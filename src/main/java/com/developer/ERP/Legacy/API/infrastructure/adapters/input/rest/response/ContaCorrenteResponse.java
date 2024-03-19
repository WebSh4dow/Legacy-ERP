package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.response;

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
public class ContaCorrenteResponse {
    private Long codigo;

    private String dataAbertura;

    private String agencia;

    private String numeroContaCorrente;

    private BigDecimal saldoInicial;

    private Moeda moeda;

    private BancoResponse banco;

    private boolean status;
}
