package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Table(name = "dados_pessoais")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosPessoaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String email;

    private String recados;
}
