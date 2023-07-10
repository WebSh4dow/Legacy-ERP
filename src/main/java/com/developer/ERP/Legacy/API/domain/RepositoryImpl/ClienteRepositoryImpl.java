package com.developer.ERP.Legacy.API.domain.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.developer.ERP.Legacy.API.domain.Model.Cliente;
import com.developer.ERP.Legacy.API.domain.Model.CriteriaFilter.ClienteCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.Model.Filter.ClienteFilter;


@Repository
public class ClienteRepositoryImpl {
	
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
		}else {
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
