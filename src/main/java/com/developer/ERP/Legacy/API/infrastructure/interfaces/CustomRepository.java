package com.developer.ERP.Legacy.API.infrastructure.interfaces;

import com.developer.ERP.Legacy.API.infrastructure.Filter.Filtro;

import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CustomRepository<T>{

    void clear() throws Exception;

    void persist(T entity);

    T merge(T entity);

    void excluir(T entity) throws Exception;

    void excluir(T entity, Integer id) throws Exception;

    void excluir(Class<T> clazz, Integer id) throws Exception;

    T getEntity(Integer id, Class<T> clazz) throws Exception;

    T getEntityJoinFetch(Integer id, Class<T> clazz) throws Exception;

    T getEntity(Class<T> clazz, String atributo, Object valor) throws Exception;

    T getEntity(Class<T> clazz, String atributo1, Object valor1, String atributo2, Object valor2) throws Exception;

    T getEntity(Class<T> clazz, List<Filtro> filtros) throws Exception;

    Long getTotalRegistros(Class<T> clazz, Map<String,Object> filters) throws Exception;

    Long setRowCount(int count, Class<T> clazz);

    Long getTotalRegistros(Class<T> clazz, Map<String,Object> filters,String atributoCount) throws Exception;

    List<T> getEntities(Class<T> clazz) throws Exception;

    List<T> getEntitiesJoinFetch(Class<T> clazz) throws Exception;

    List<T> getEntities(Class<T> clazz, String atributo, Object valor) throws Exception;

    List<T> getEntitiesLike(Class<T> clazz, String atributo, String valor) throws Exception;

    List<T> getEntities(Class<T> clazz, String atributo1, Object valor1, String atributo2, Object valor2) throws Exception;

    List<T> getEntities(Class<T> clazz, String nomeAtributoData, Date dataInicio, Date dataFim);

    List<T> getEntities(Class<T> clazz, String atributo, Object valor, String nomeAtributoData, Date dataInicio, Date dataFim);

    List<T> getEntities(Class<T> clazz, List<Filtro> filtros) throws Exception;

    List<T> getEntities(Class<T> clazz, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) throws Exception;

    List<T> getEntities(Class<T> clazz, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters, String[] atributos) throws Exception;


}
