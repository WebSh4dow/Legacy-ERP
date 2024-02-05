package com.developer.ERP.Legacy.API.infrastructure.service;

import com.developer.ERP.Legacy.API.infrastructure.Filter.Filtro;
import com.developer.ERP.Legacy.API.infrastructure.interfaces.CustomRepository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.lang.reflect.Field;

@Component
public class GenericService<T> implements CustomRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericService () {}


    @Override
    public void clear() throws Exception {
        entityManager.clear();
    }

    @Override
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T merge(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void excluir(T entity) throws Exception {
        entityManager.remove(entityManager.merge(entity));
    }

    @Override
    public void excluir(T entity, Integer id) throws Exception {
        entityManager.remove(entityManager.getReference(entity.getClass(), id));
    }

    @Override
    public void excluir(Class<T> clazz, Integer id) throws Exception {
        Query query = entityManager.createQuery("DELETE FROM " + clazz.getName() + " o WHERE o.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public T getEntity(Integer id, Class<T> clazz) throws Exception {
        return entityManager.find(clazz, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getEntityJoinFetch(Integer id, Class<T> clazz) throws Exception {
        String jpql = "SELECT DISTINCT o FROM " + clazz.getName() + " o";
        Field fields[] = clazz.getDeclaredFields();

        for (Field f : fields) {
            if (f.getType() == Set.class) {
                jpql += " LEFT JOIN FETCH o." + f.getName();
            }
        }

        jpql += " WHERE o.id = :id";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);

        try {
            return (T) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public T getEntity(Class<T> clazz, String atributo, Object valor) throws Exception {
        List<T> entities = getEntities(clazz, atributo, valor);

        if (entities.isEmpty()) {
            return null;
        } else {
            return entities.get(0);
        }
    }

    @Override
    public T getEntity(Class<T> clazz, String atributo1, Object valor1, String atributo2, Object valor2) throws Exception {
         List<T> entities = getEntities(clazz, atributo1, valor1, atributo2, valor2);

         if (entities.isEmpty()) {
             return null;
         } else {
             return entities.get(0);
         }

    }

    @Override
    @SuppressWarnings("unchecked")
    public T getEntity(Class<T> clazz, List<Filtro> filtros) throws Exception {
        String jpql = "SELECT o FROM " + clazz.getName() + " o WHERE 1 = 1";

        int i = 0;

        for (Filtro f : filtros) {
            i++;
            jpql += " " + f.getOperadorLogico() + "o." + f.getAtributo() + f.getOperadorRelacional() + ":valor" + i;
        }

        Query query = entityManager.createQuery(jpql);

        i = 0;

        for (Filtro f : filtros) {
            i++;
            query.setParameter("valor" + i, f.getValor());
        }

        try {
            return (T) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long getTotalRegistros(Class<T> clazz, Map<String, Object> filters, String atributoCount) throws Exception {
        atributoCount = atributoCount == null ? "id" : atributoCount;

        String jpql = "SELECT COUNT(o." + atributoCount + ") FROM " + clazz.getName() + " o WHERE 1 = 1";

        for (Iterator<String> iterator = filters.keySet().iterator(); iterator.hasNext();) {
            String atributo = iterator.next();
            Object valor = filters.get(atributo);
            if (valor != null) {
                if (valor.getClass() == String.class) {
                    jpql += " AND LOWER(o." + atributo + "= :" + atributo;
                }
            }
        }

        Query query = entityManager.createQuery(jpql);

        for(Iterator<String> iterator = filters.keySet().iterator();iterator.hasNext();) {
            String atributo = iterator.next();
            Object valor = filters.get(atributo);
            if (valor != null) {
                if (valor.getClass() == String.class) {
                    query.setParameter(atributo, "%" + String.valueOf(valor).toLowerCase() + "%");
                } else {
                    query.setParameter(atributo,valor);
                }
            }
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getTotalRegistros(Class<T> clazz, Map<String, Object> filters) throws Exception {
        return getTotalRegistros(clazz, filters, null);
    }

    @Override
    public Long setRowCount(int count, Class<T> clazz) {
        Query query = entityManager.createQuery
                ("SELECT COUNT (" + count + ") FROM " + clazz.getName() + " o");
        return (long) query.getSingleResult();
    }

    @Override
    public List<T> getEntities(Class<T> clazz) throws Exception {
        CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
        Root<T> entitie = criteria.from(clazz);
        criteria.select(entitie);
        return entityManager.createQuery(criteria).getResultList();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getEntitiesJoinFetch(Class<T> clazz) throws Exception {
        String jpql = "SELECT DISTINCT o FROM " + clazz.getName() + " o";
        Field fields[] = clazz.getDeclaredFields();

        for (Field f : fields) {
            if (f.getType() == Set.class) {
                jpql += " LEFT JOIN FETCH o." + f.getName();
            }
        }
        Query query = entityManager.createQuery(jpql);
        List<T> entities = query.getResultList();
        return entities;
    }

    @Override
    public List<T> getEntities(Class<T> clazz, String atributo, Object valor) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.select(root);

        if (valor.getClass() == String.class) {
            Path<String> nome = root.get(atributo);
            criteria.where(builder.equal(nome, valor));
        } else if (valor.getClass() == Integer.class) {
            Path<Integer> nome = root.get(atributo);
            criteria.where(builder.equal(nome, valor));
        } else if (valor.getClass() == Date.class) {
            Path<Date> nome = root.get(atributo);
            criteria.where(builder.equal(nome, valor));
        } else if (valor.getClass() == BigDecimal.class) {
            Path<BigDecimal> nome = root.get(atributo);
            criteria.where(builder.equal(nome, valor));
        }

        TypedQuery<T> query = entityManager.createQuery(criteria);
        List<T> entities = query.getResultList();
        return entities;
    }

    @Override
    public List<T> getEntitiesLike(Class<T> clazz, String atributo, String valor) throws Exception {
        Query query = entityManager.createQuery("SELECT o FROM " + clazz.getName() + " o WHERE LOWER(o." + atributo + ") like :valor");
        query.setParameter("valor", "%" + valor.trim().toLowerCase() + "%");
        query.setMaxResults(100);
        List<T> entities = query.getResultList();
        return entities;
    }

    @Override
    public List<T> getEntities(Class<T> clazz, String atributo1, Object valor1, String atributo2, Object valor2) throws Exception {
        Query query = entityManager.createQuery("SELECT o FROM " + clazz.getName() + " o WHERE o." + atributo1 + " = :valor1 " + " AND o." + atributo2 + " = :valor2");
        query.setParameter("valor1", valor1);
        query.setParameter("valor2", valor2);
        List<T> entities = query.getResultList();
        return entities;
    }

    @Override
    public List<T> getEntities(Class<T> clazz, String nomeAtributoData, Date dataInicio, Date dataFim) {
        Query query = entityManager.createQuery("SELECT o FROM " + clazz.getName() + " o WHERE o. " + nomeAtributoData + " BETWEEN :dataInicio AND :dataFim");
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);
        List<T> entities = query.getResultList();
        return entities;
    }

    @Override
    public List<T> getEntities(Class<T> clazz, String atributo, Object valor, String nomeAtributoData, Date dataInicio, Date dataFim) {
        Query query = entityManager.createQuery("SELECT o FROM " + clazz.getName() + " o WHERE o." + nomeAtributoData + " BETWEEN :dataInicio AND :dataFim " + " AND o." + atributo + " = :valor");
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);
        query.setParameter("valor", valor);
        List<T> entities = query.getResultList();
        return entities;
    }

    @Override
    public List<T> getEntities(Class<T> clazz, List<Filtro> filtros) throws Exception {
        String jpql = "SELECT o FROM " + clazz.getName() + " o WHERE 1 = 1";
        int i = 0;
        for (Filtro f : filtros) {
            i++;
            jpql += " " + f.getOperadorLogico() + " o." + f.getAtributo() + f.getOperadorRelacional() + ":valor" + i;
        }
        Query query = entityManager.createQuery(jpql);

        i = 0;
        for (Filtro f : filtros) {
            i++;
            query.setParameter("valor" + i, f.getValor());
        }
        List<T> entities = query.getResultList();
        return entities;
    }

    @Override
    public List<T> getEntities(Class<T> clazz, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) throws Exception {
        String jpql = "SELECT o FROM " + clazz.getName() + " o WHERE 1 = 1";

        return montaQuery(jpql, first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public List<T> getEntities(Class<T> clazz, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters, String[] atributos) throws Exception {
        String jpql = "SELECT NEW " + clazz.getName() + "(o.id ";
        for (String s : atributos) {
            jpql += ", o." + s;
        }
        jpql += ") FROM " + clazz.getName() + " o WHERE 1 = 1";
        return montaQuery(jpql, first, pageSize, sortField, sortOrder, filters);
    }

    @SuppressWarnings("unchecked")
    private List<T> montaQuery(String jpql, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
            String atributo = it.next();
            Object valor = filters.get(atributo);
            if (valor != null) {
                if (valor.getClass() == String.class) {
                    jpql += " AND LOWER(o." + atributo + ") like :" + atributo;
                } else {
                    jpql += " AND o." + atributo + " = :" + atributo;
                }
            }
        }

        if (sortField != null && sortOrder != null) {
            if (sortOrder.equals(SortOrder.ASCENDING)) {
                jpql += " ORDER BY o." + sortField + " ASC";
            } else if (sortOrder.equals(SortOrder.DESCENDING)) {
                jpql += " ORDER BY o." + sortField + " DESC";
            }
        }

        Query query = entityManager.createQuery(jpql);

        for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
            String atributo = it.next();
            Object valor = filters.get(atributo);
            if (valor != null) {
                if (valor.getClass() == String.class) {
                    query.setParameter(atributo, "%" + String.valueOf(valor).toLowerCase() + "%");
                } else {
                    query.setParameter(atributo, valor);
                }
            }
        }

        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        List<T> entities = query.getResultList();
        return entities;
    }
}
