package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.response;

import com.developer.ERP.Legacy.API.domain.enums.Nacionalidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConjugueResponse {

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
