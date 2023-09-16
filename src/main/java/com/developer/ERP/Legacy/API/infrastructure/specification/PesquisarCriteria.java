package com.developer.ERP.Legacy.API.infrastructure.specification;

import com.developer.ERP.Legacy.API.infrastructure.specification.abstractions.PesquisarPageable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PesquisarCriteria<T> extends PesquisarPageable {
    private T search;
}
