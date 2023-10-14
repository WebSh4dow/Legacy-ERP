package com.developer.ERP.Legacy.API.infrastructure.repositoryImpl.entitymanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.model.Contratos;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteSpecFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;
import com.developer.ERP.Legacy.API.infrastructure.repository.CustomClienteRepository;


@Repository
public class ClienteEntityManagerRepository extends EntityManagerCustomRepository implements CustomClienteRepository {
	

	private final EntityManager entityManager;

	private final CriteriaBuilder criteriaBuilder;


    public ClienteEntityManagerRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}
    
    @Override
    public Long getClienteCount(Predicate predicate) {
    	CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
    	Root<Cliente> countRoot = countCriteriaQuery.from(Cliente.class);
    	countCriteriaQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
    	return entityManager.createQuery(countCriteriaQuery).getSingleResult();
    }
    
    @Override
    public Pageable getPageable(ClienteFilter clienteFilter) {
    	Sort sort = Sort.by(clienteFilter.getSortDirection(),clienteFilter.getSortBy());
    	return PageRequest.of(clienteFilter.getPageNumber(),clienteFilter.getPageSize(),sort);
    }
    
    @Override
    public void setOrder(ClienteFilter clienteFilter,CriteriaQuery<Cliente>criteriaQuery,Root<Cliente>clientesRoot) {
    	if (clienteFilter.getSortDirection().equals(Sort.Direction.ASC)) {
			criteriaQuery.orderBy(criteriaBuilder.asc(clientesRoot.get(clienteFilter.getSortBy())));
		}else{
			criteriaQuery.orderBy(criteriaBuilder.desc(clientesRoot.get(clienteFilter.getSortBy())));
		}
    }
    
    @Override
    public Predicate getPredicate(ClienteSpecFilter clienteCriteriaFilter, Root<Cliente>clienteRoot) {
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
    
    @Override
    public Page<Cliente> buscarClientes(ClienteFilter clienteFilter,ClienteSpecFilter clienteCriteriaFilter) {
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
    
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Cliente> buscarClientePorId(Long id) {
		
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);
		
		Criteria criteria =super.createCriteria(entityManager, Cliente.class);
		
		criteria.add(Restrictions.eq("id", id));
		List<Cliente> listClientes = criteria.list();
	
		return listClientes;
	}
	

    public List<Contratos> clientePossuiContratosExpirados(Long idCliente) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contratos> cq = cb.createQuery(Contratos.class);
        Root<Contratos> contratoRoot = cq.from(Contratos.class);

        List<Predicate> predicates = new ArrayList<>();

        if (idCliente != null) {
            predicates.add(cb.equal(contratoRoot.get("cliente").get("id"), idCliente));
        }
        
        predicates.add(cb.lessThan(contratoRoot.get("dataVencimento"), LocalDate.now()));
        predicates.add(cb.lessThan(contratoRoot.get("dataInicial"), LocalDate.now()));

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

	@Override
	public Page<Cliente> buscarClientesPorIdPageable(ClienteFilter clienteFilter,
			ClienteSpecFilter clienteCriteriaFilter, Long id) {
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> clienteRoot = criteriaQuery.from(Cliente.class);
		Predicate predicate = getPredicate(clienteCriteriaFilter, clienteRoot);

		criteriaQuery.where(predicate);
		setOrder(clienteFilter, criteriaQuery, clienteRoot);

		TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);

		typedQuery.setFirstResult(clienteFilter.getPageNumber() * clienteFilter.getPageSize());
		typedQuery.setMaxResults(clienteFilter.getPageSize());

		Pageable pageable = getPageable(clienteFilter);
		List<Cliente> buscarCliente = buscarClientePorId(id);

		long countClientes = getClienteCount(predicate);

		return new PageImpl<>(buscarCliente, pageable, countClientes);
	}



}
