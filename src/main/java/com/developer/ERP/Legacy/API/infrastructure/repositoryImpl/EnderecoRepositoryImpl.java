package com.developer.ERP.Legacy.API.infrastructure.repositoryImpl;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.developer.ERP.Legacy.API.domain.model.Endereco;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.EnderecoCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.EnderecoFilter;

@Repository
public class EnderecoRepositoryImpl {
	
	private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    
    public EnderecoRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}
    
    private Long getEnderecoCount(Predicate predicate) {
    	CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
    	Root<Endereco> countRoot = countCriteriaQuery.from(Endereco.class);
    	countCriteriaQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
    	return entityManager.createQuery(countCriteriaQuery).getSingleResult();
    }
    
    private Pageable getPageable(EnderecoFilter enderecoFilter) {
    	Sort sort = Sort.by(enderecoFilter.getSortDirection(),enderecoFilter.getSortBy());
    	return PageRequest.of(enderecoFilter.getPageNumber(),enderecoFilter.getPageSize(),sort);
    }
    
    private void setOrder(EnderecoFilter enderecoFilter,CriteriaQuery<Endereco>criteriaQuery,Root<Endereco>enderecoRoot) {
    	if (enderecoFilter.getSortDirection().equals(Sort.Direction.ASC)) {
			criteriaQuery.orderBy(criteriaBuilder.asc(enderecoRoot.get(enderecoFilter.getSortBy())));
		}else {
			criteriaQuery.orderBy(criteriaBuilder.desc(enderecoRoot.get(enderecoFilter.getSortBy())));
		}
    }
    
    private Predicate getPredicate(EnderecoCriteriaFilter enderecoCriteriaFilter, Root<Endereco>enderecoRoot) {
    	List<Predicate> predicates = new ArrayList<>();
    	if (Objects.nonNull(enderecoCriteriaFilter.getCep())) {
			predicates.add(
					criteriaBuilder.like(enderecoRoot.get("cep"), 
							"%" + enderecoCriteriaFilter.getCep() + "%")
			);
		}
    	if (Objects.nonNull(enderecoCriteriaFilter.getBairro())) {
			predicates.add(
					criteriaBuilder.like(enderecoRoot.get("bairro"),
						   "%" + enderecoCriteriaFilter.getBairro() + "%")
			);
		}
    	return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
    
    public Page<Endereco> buscarTodosEnderecos(EnderecoFilter enderecoFilter,EnderecoCriteriaFilter enderecoCriteriaFilter) {
    	CriteriaQuery<Endereco>criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
    	Root<Endereco> enderecoRoot = criteriaQuery.from(Endereco.class);
    	Predicate predicate = getPredicate(enderecoCriteriaFilter, enderecoRoot);
    	
    	criteriaQuery.where(predicate);
        setOrder(enderecoFilter, criteriaQuery, enderecoRoot);
        
    	TypedQuery<Endereco> typedQuery = entityManager.createQuery(criteriaQuery);
    	typedQuery.setFirstResult(enderecoFilter.getPageNumber() * enderecoFilter.getPageSize());
    	typedQuery.setMaxResults(enderecoFilter.getPageSize());
    	
    	Pageable pageable  = getPageable(enderecoFilter);
    	long countEnderecos = getEnderecoCount(predicate);
    	
    	return new PageImpl<>(typedQuery.getResultList(),pageable,countEnderecos);
    }
    
}
