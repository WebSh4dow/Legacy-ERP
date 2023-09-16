package com.developer.ERP.Legacy.API.infrastructure.specification.abstractions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import lombok.Data;
import java.io.Serial;

@Data
public abstract class PesquisarSpecification<S,T> implements Specification<T> {

    @Serial
    private static final long serialVersionUID = 1L;

    private S search;

    public PesquisarSpecification(S search){
        this.search = search;
    }

    public abstract Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, S search);

    @Override
    public javax.persistence.criteria.Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return this.toPredicate(root, query, criteriaBuilder,this.search);
    }
}
