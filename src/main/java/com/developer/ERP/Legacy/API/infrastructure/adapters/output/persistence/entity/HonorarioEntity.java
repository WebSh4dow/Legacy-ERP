package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "honorario")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HonorarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private BigDecimal administra = BigDecimal.ZERO;

    private BigDecimal primeiroAluguel = BigDecimal.ZERO;

    private BigDecimal venda = BigDecimal.ZERO;

    private Integer repasseDias;
}
