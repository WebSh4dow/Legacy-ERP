package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity;

import com.developer.ERP.Legacy.API.domain.enums.Nacionalidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Table(name = "conjugue")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConjugueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private String cpf;

    private String rg;

    private String dataNascimento;

    private String natura;

    @Enumerated(EnumType.STRING)
    private Nacionalidade nacionalidade;

    private String regimeBens;

    private String certNascimento;

    private String profissao;

}
