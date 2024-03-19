package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.request;

import com.developer.ERP.Legacy.API.domain.enums.Moeda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrenteRequest {

    @NotBlank(message = "data de abertura é um campo obrigatório")
    private String dataAbertura;

    @NotBlank(message = "agência é um campo obrigatório")
    private String agencia;

    @NotBlank(message = "numero da conta corrente é um campo obrigatório")
    private String numeroContaCorrente;

    private BigDecimal saldoInicial;

    private Moeda moeda;

    private BancoRequest banco;

    private boolean status;

}
