package com.developer.ERP.Legacy.API.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Moeda {
    REAL("R$"),
    DOLAR_US("US$"),
    EURO("€"),
    LIBRA_ESTERLINA("£"),
    IENE("¥"),
    FRANCO_SUIÇO("CHF"),
    DOLAR_CAN("C$"),
    PESO_ARG("AR$"),
    PESO_MEX("Mex$"),
    PESO_COL("COL$"),
    PESO_CHI("CLP$");

    private String descricao;
}
