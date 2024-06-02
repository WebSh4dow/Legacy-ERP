package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.request;

import com.developer.ERP.Legacy.API.domain.enums.UF;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequest {

    @NotBlank
    private String descricao;

    @NotBlank
    private String cep;

    @NotBlank
    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    private UF uf;
}
