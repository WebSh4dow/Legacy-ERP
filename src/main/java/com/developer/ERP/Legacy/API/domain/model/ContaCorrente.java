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
public class ContaCorrente {

    private Long codigo;

    private String dataAbertura;

    private String agencia;

    private String numeroContaCorrente;

    private BigDecimal saldoInicial = BigDecimal.ZERO;

    private Banco banco;

    private Moeda moeda;

    private boolean status;

    // private List <Lancamentos> lancamentos; conta a receber e a pagar e avulsos
}
