package com.developer.ERP.Legacy.API.infrastructure.lazy;

import com.developer.ERP.Legacy.API.infrastructure.interfaces.CustomRepository;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LazyDataModel<T> {
    private static final long serialVersionUID = 1L;
    protected CustomRepository<T> repository;
    private Class<T> clazz;

    public T getRowData(String rowKey) {
        try {
            return repository.getEntityJoinFetch(Integer.valueOf(rowKey), getClazz());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T setRowData(Long id, T entity) {
        if (id != null) {
            try {
                Method setIdMethod = entity.getClass().getMethod("setId", Long.class);
                setIdMethod.invoke(entity, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return repository.merge(entity);
    }

    public Object getRowKey(T object) {
        try {
            Method metodo = object.getClass().getDeclaredMethod("getId");
            return metodo.invoke(object);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            List<T> beans = repository.getEntities(getClazz(), first, pageSize, sortField, sortOrder, filters);
            Long totalRegistros = repository.getTotalRegistros(getClazz(), filters);

            this.repository.setRowCount(totalRegistros.intValue(),getClazz());
            return beans;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public CustomRepository<T> getRepository() {
        return repository;
    }

    public void setRepository(CustomRepository<T> dao) {
        this.repository = dao;
    }

}
