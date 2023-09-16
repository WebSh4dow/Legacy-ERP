package com.developer.ERP.Legacy.API.infrastructure.specification.abstractions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class PesquisarPageSpecification<S extends PesquisarPageable,T> extends PesquisarSpecification<S,T> {

    private static final String DEFAULT_SORT_ASC = "asc";
    private static final String DEFAULT_SORT_DESC = "desc";
    public PesquisarPageSpecification(S search) {
        super(search);
    }
    protected String sortProperty(String sortField){
        return sortField;
    }
    protected Sort buildSort(String sort, String sortField){
        return switch (sort) {
            case DEFAULT_SORT_ASC -> Sort.by(Sort.Order.asc(sortField));
            case DEFAULT_SORT_DESC -> Sort.by(Sort.Order.desc(sortField));
            default -> Sort.by(Sort.Order.asc(sortField));
        };
    }
    public Pageable getPageable(){
        PesquisarPageable pesquisarPageable = super.getSearch();

        Integer page = pesquisarPageable.getPage();
        Integer size = pesquisarPageable.getSize();

        String sort = StringUtils.defaultString(pesquisarPageable.getSort(),DEFAULT_SORT_ASC);
        String preSortField = this.sortProperty(pesquisarPageable.getSortField());

        String sortField = StringUtils.defaultString(preSortField,"id");
        return PageRequest.of(page,size,buildSort(sort,sortField));

    }
}
