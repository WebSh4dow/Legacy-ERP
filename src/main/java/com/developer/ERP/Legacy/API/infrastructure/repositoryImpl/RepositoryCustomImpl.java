package com.developer.ERP.Legacy.API.infrastructure.repositoryImpl;

import java.util.Iterator;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.Assert;

import com.developer.ERP.Legacy.API.domain.exceptions.runtime.HandlerNotFoundException;

public class RepositoryCustomImpl {
	
	/*/
	 * pega a sessão atual do entitymanager definido
	 */
	protected final Session getSession(EntityManager em) {
		return em.unwrap(Session.class);
		
	}
	
	/*/
	 * obtem a quantidade de registros retornados da consulta
	 */
	
	protected final int rowCount(Criteria query) {
		Assert.notNull(query,"criteria");
		query.setProjection(Projections.rowCount());
		
		Object rowCount = query.uniqueResult();
		
		Assert.notNull(rowCount,"rowCount");
		
		query.setProjection(null);
		query.setResultTransformer(Criteria.ROOT_ENTITY);
		
		try {
			return Integer.parseInt(rowCount.toString());
		} catch (Exception e) {
			throw new HandlerNotFoundException("Ocorreu algum erro ao tentar obter o totalizador dos registros.");
		}
	}
	
	@SuppressWarnings("deprecation")
	protected final Criteria createCriteria(EntityManager em, Class<?> clazz) {
		return this.getSession(em).createCriteria(clazz);
				
	}

	public final void paginar(Criteria query, Pageable pageable) {
		int size = pageable != null ? pageable.getPageSize() : -1;
		
		if (size < 0) {
			return;
		}
		
		int start = pageable.getPageNumber() * size;
		
		query.setFirstResult(start);
		query.setMaxResults(size);
		
	}

	public final void criarSort(Criteria criteria, Pageable pageable) {

		if (pageable == null)
			return;

		Assert.notNull(criteria, "criteria");

		Sort sort = pageable.getSort();

		if (sort == null)
			return;

		for (Iterator<Order> i = sort.iterator(); i.hasNext();) {

			Order order = i.next();
			String property = order.getProperty();
			boolean isAscDirection = order.getDirection() == null || order.getDirection().equals(Direction.ASC);
			org.hibernate.criterion.Order criteriaOrder = null;

			if (isAscDirection) {
				criteriaOrder = org.hibernate.criterion.Order.asc(property);
			} else {
				criteriaOrder = org.hibernate.criterion.Order.desc(property);
			}

			criteria.addOrder(criteriaOrder);

		}
		
	}
}
