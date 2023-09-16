package com.developer.ERP.Legacy.API.infrastructure.specification.abstractions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PesquisarPageable {
    private Integer page = 0;
    private Integer size = 10;
    private String sort;
    private String sortField;

}
