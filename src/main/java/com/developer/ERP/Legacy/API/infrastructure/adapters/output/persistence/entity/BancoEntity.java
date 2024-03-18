package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Table(name = "banco")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BancoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String descricao;
}
