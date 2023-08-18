package com.developer.ERP.Legacy.API.infrastructure.repositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.model.Contratos;
import com.developer.ERP.Legacy.API.domain.repository.ClienteRepository;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;
import com.developer.ERP.Legacy.API.infrastructure.config.RepositoryCustomImpl;


@Repository
public class ClienteRepositoryImpl extends RepositoryCustomImpl {
	

	private final EntityManager entityManager;
	
    private final CriteriaBuilder criteriaBuilder;
    
   
    
    public ClienteRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}
    
    private Long getClienteCount(Predicate predicate) {
    	CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
    	Root<Cliente> countRoot = countCriteriaQuery.from(Cliente.class);
    	countCriteriaQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
    	return entityManager.createQuery(countCriteriaQuery).getSingleResult();
    }
    
    private Pageable getPageable(ClienteFilter clienteFilter) {
    	Sort sort = Sort.by(clienteFilter.getSortDirection(),clienteFilter.getSortBy());
    	return PageRequest.of(clienteFilter.getPageNumber(),clienteFilter.getPageSize(),sort);
    }
    
    private void setOrder(ClienteFilter clienteFilter,CriteriaQuery<Cliente>criteriaQuery,Root<Cliente>clientesRoot) {
    	if (clienteFilter.getSortDirection().equals(Sort.Direction.ASC)) {
			criteriaQuery.orderBy(criteriaBuilder.asc(clientesRoot.get(clienteFilter.getSortBy())));
		}else{
			criteriaQuery.orderBy(criteriaBuilder.desc(clientesRoot.get(clienteFilter.getSortBy())));
		}
    }
    
    private Predicate getPredicate(ClienteCriteriaFilter clienteCriteriaFilter, Root<Cliente>clienteRoot) {
    	List<Predicate> predicates = new ArrayList<>();
    	if (Objects.nonNull(clienteCriteriaFilter.getNome())) {
			predicates.add(
					criteriaBuilder.like(clienteRoot.get("nome"), 
							"%" + clienteCriteriaFilter.getNome() + "%")
			);
		}
    	if (Objects.nonNull(clienteCriteriaFilter.getSobreNome())) {
			predicates.add(
					criteriaBuilder.like(clienteRoot.get("sobreNome"),
						   "%" + clienteCriteriaFilter.getSobreNome() + "%")
			);
		}
    	return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
    
    public Criteria buscarContratosClienteByCriteriaFilter(Long clientId) {
        @SuppressWarnings("deprecation")
		Criteria criteria = this.getSession(entityManager).createCriteria(Cliente.class, "cl");
        
        criteria.createAlias("cl.contratos", "c", JoinType.INNER_JOIN);
        criteria.createAlias("cl.pessoaJuridica", "pj", JoinType.INNER_JOIN);
        
        criteria.add(Restrictions.eq("cl.id", clientId));
        
        criteria.setProjection(Projections.projectionList()
            .add(Projections.property("cl.id"), "clienteId")
            .add(Projections.property("c.id"), "contratoId")
            .add(Projections.property("cl.nome"))
            .add(Projections.property("cl.sobrenome"))
            .add(Projections.property("cl.isAtivo"))
            .add(Projections.property("cl.historico"))
            .add(Projections.property("cl.outros.id"), "terceiro")
            .add(Projections.property("cl.pessoaFisica.id"), "pessoaFisica")
            .add(Projections.property("cl.pessoaJuridica.id"), "pessoaJuridica")
       
        );
        criteria.list();
        return criteria;
    }
    
    public Page<Cliente>buscarContratosClienteByCriteriaFilterPaginator(ClienteCriteriaFilter clienteFilter, Pageable pageable){
    	return this.buscarContratosClienteByCriteriaFilterPaginator(clienteFilter, pageable,true);
    }
    
	public Page<Cliente> buscarContratosClienteByCriteriaFilterPaginator(ClienteCriteriaFilter clienteFilter,Pageable pageable, boolean paginar) {
		
		Criteria criteriaContratosByClientes = buscarContratosClienteByCriteriaFilter(clienteFilter.getId());
		int total = super.rowCount(criteriaContratosByClientes);
		
		if (paginar) {
			super.paginar(criteriaContratosByClientes, pageable);
		}
		super.criarSort(criteriaContratosByClientes, pageable);

		@SuppressWarnings("unchecked")
		List<Cliente> result = (List<Cliente>) criteriaContratosByClientes.list();
		return new PageImpl<>(result, pageable, total);
	}

    public Page<Cliente> buscarClientes(ClienteFilter clienteFilter,ClienteCriteriaFilter clienteCriteriaFilter) {
    	CriteriaQuery<Cliente>criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
    	Root<Cliente> clienteRoot = criteriaQuery.from(Cliente.class);
    	Predicate predicate = getPredicate(clienteCriteriaFilter, clienteRoot);
    	
    	criteriaQuery.where(predicate);
        setOrder(clienteFilter, criteriaQuery, clienteRoot);
        
    	TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
    	typedQuery.setFirstResult(clienteFilter.getPageNumber() * clienteFilter.getPageSize());
    	typedQuery.setMaxResults(clienteFilter.getPageSize());
    	
    	Pageable pageable  = getPageable(clienteFilter);
    	long countClientes = getClienteCount(predicate);
    	
    	return new PageImpl<>(typedQuery.getResultList(),pageable,countClientes);
    }

}
