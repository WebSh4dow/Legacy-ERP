package com.developer.ERP.Legacy.API.infrastructure.Filter;

import lombok.Data;

import java.io.Serializable;

@Data
public class Filtro implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String AND = "AND";
    public static final String OR = "OR";
    public static final String NOT = "NOT";
    public static final String IGUAL = "=";
    public static final String DIFERENTE = "<>";
    public static final String MENOR = "<";
    public static final String MENOR_OU_IGUAL = "<=";
    public static final String MAIOR = ">";
    public static final String MAIOR_OU_IGUAL = ">=";
    public static final String NAO_NULO = "NOT NULL";
    private String operadorLogico;
    private String atributo;
    private String operadorRelacional;
    private Object valor;
}
