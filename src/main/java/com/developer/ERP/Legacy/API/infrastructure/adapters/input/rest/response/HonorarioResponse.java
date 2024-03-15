package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HonorarioResponse {


    private BigDecimal administra;

    private BigDecimal primeiroAluguel;

    private BigDecimal venda;

    private Integer repasseDias;
}
