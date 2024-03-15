package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HonorarioRequest {

    private BigDecimal administra;

    private BigDecimal primeiroAluguel;

    private BigDecimal venda ;

    private Integer repasseDias;
}
