package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.request;

import com.developer.ERP.Legacy.API.domain.enums.Nacionalidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConjugueRequest {

    private Long codigo;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String rg;

    @NotBlank
    private String dataNascimento;

    @NotBlank
    private String natura;

    private Nacionalidade nacionalidade;

    @NotBlank
    private String regimeBens;

    @NotBlank
    private String certNascimento;

    @NotBlank
    private String profissao;
}
