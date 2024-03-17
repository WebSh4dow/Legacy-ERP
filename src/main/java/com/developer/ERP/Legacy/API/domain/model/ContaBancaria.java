package com.developer.ERP.Legacy.API.domain.model;

import com.developer.ERP.Legacy.API.domain.enums.Moeda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContaBancaria {

    private Long codigo;

    private String dataAbertura;

    private String agencia;

    private String numeroContaCorrente;

    private BigDecimal saldoInicial = BigDecimal.ZERO;

    private String banco;

    private Moeda moeda;

    private boolean status;
}
