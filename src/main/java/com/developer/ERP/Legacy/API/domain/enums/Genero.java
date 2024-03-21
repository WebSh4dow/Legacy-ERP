package com.developer.ERP.Legacy.API.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genero {

    CONFIDENCIAL("Confidencial"),

    FEMININO("Feminino"),

    JURIDICA("Judicial"),

    INATIVO("Inativo");

    private String descricao;


}
