package com.developer.ERP.Legacy.API.domain.model;

import com.developer.ERP.Legacy.API.domain.enums.Nacionalidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conjugue {

    private Long codigo;

    private String nome;

    private String cpf;

    private String rg;

    private String dataNascimento;

    private String natura;

    private Nacionalidade nacionalidade;

    private String regimeBens;

    private String certNascimento;

    private String profissao;
}
