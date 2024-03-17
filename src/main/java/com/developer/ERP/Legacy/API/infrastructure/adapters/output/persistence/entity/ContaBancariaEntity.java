package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity;

import com.developer.ERP.Legacy.API.domain.enums.Moeda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "conta_bancaria")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContaBancariaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String dataAbertura;

    private String agencia;

    private String numeroContaCorrente;

    private BigDecimal saldoInicial = BigDecimal.ZERO;

    private String banco;

    @Enumerated(EnumType.STRING)
    private Moeda moeda;

    private boolean status;
}
