package com.developer.ERP.Legacy.API.core.validation.validators.impl.validatorFactory;

import com.developer.ERP.Legacy.API.core.validation.validators.entityManager.EntityManagerAwareValidator;
import org.springframework.dao.DataIntegrityViolationException;
import javax.persistence.EntityManagerFactory;
import javax.validation.ConstraintValidator;

public class ConstraintValidatorFactory implements javax.validation.ConstraintValidatorFactory {

    private EntityManagerFactory entityManagerFactory;


    public ConstraintValidatorFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
        T instace = null;

        try {
            instace = key.newInstance();
        }catch (Exception e){
            throw  new DataIntegrityViolationException("Erro de violação de dados" + e);
        }
        if (EntityManagerAwareValidator.class.isAssignableFrom(key)){
            EntityManagerAwareValidator validator = (EntityManagerAwareValidator) instace;
            validator.setEntityManager(entityManagerFactory.createEntityManager());
        }
        return instace;
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> instance) {

    }
}
