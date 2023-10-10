package com.developer.ERP.Legacy.API.core.validation.validators.entityManager;

import javax.persistence.EntityManager;

public interface EntityManagerAwareValidator {
    void setEntityManager(EntityManager entityManager);
}
