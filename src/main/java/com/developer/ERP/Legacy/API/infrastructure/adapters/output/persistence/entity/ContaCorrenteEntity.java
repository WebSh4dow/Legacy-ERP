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
@Table(name = "conta_corrente")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContaCorrenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String dataAbertura;

    private String agencia;

    private String numeroContaCorrente;

    private BigDecimal saldoInicial = BigDecimal.ZERO;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_banco")
    private BancoEntity banco;

    @Enumerated(EnumType.STRING)
    private Moeda moeda;

    private boolean status;
}
