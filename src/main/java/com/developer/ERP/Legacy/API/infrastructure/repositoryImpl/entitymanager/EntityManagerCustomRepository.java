package com.developer.ERP.Legacy.API.infrastructure.repositoryImpl.entitymanager;

import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;

public class EntityManagerCustomRepository {
	
	/*/
	 * pega a sessão atual do entitymanager definido
	 */
	protected final Session getSession(EntityManager em) {
		return em.unwrap(Session.class);
		
	}


	@SuppressWarnings("deprecation")
	protected final Criteria createCriteria(EntityManager em, Class<?> clazz) {
		return this.getSession(em).createCriteria(clazz);
				
	}

}
