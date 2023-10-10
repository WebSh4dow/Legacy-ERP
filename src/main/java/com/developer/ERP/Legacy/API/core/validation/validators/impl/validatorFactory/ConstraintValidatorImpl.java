package com.developer.ERP.Legacy.API.core.validation.validators.impl.validatorFactory;

import com.developer.ERP.Legacy.API.core.annotations.UniqueKeyValidator;
import com.developer.ERP.Legacy.API.core.validation.validators.entityManager.EntityManagerAwareValidator;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConstraintValidatorImpl implements ConstraintValidator<UniqueKeyValidator, Serializable>, EntityManagerAwareValidator {
    @PersistenceContext
    private EntityManager entityManager;

    private String [] nomePropriedades;

    @Override
    public void initialize(UniqueKeyValidator constraintAnnotation) {
       this.nomePropriedades = constraintAnnotation.nomePropiedades();
    }

    @Override
    public boolean isValid(Serializable value, ConstraintValidatorContext context) {
        Class<?> entityClass = value.getClass();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();

        Root<?> root = criteriaQuery.from(entityClass);

        List<Predicate> predicates = new ArrayList<Predicate>(nomePropriedades.length);

        try {
            for (int i = 0; i < this.nomePropriedades.length; i++){
                String nomePropriedades = this.nomePropriedades[i];
                PropertyDescriptor descriptor = new PropertyDescriptor(nomePropriedades,entityClass);
                Method readMethod = descriptor.getReadMethod();
                Object propertyValue = readMethod.invoke(value);
                Predicate predicate = criteriaBuilder.equal(root.get(nomePropriedades),propertyValue);
                predicates.add(predicate);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Object> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object> resultQuery = typedQuery.getResultList();

        return resultQuery.size() == 0;

    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
