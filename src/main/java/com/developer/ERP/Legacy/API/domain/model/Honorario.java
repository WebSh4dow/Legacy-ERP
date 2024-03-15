package com.developer.ERP.Legacy.API.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Honorario {

    private Long codigo;

    private BigDecimal administra = BigDecimal.ZERO;

    private BigDecimal primeiroAluguel = BigDecimal.ZERO;

    private BigDecimal venda = BigDecimal.ZERO;

    private Integer repasseDias;
}
