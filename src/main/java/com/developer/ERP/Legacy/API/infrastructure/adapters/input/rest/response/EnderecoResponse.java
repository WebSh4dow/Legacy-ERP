package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.response;

import com.developer.ERP.Legacy.API.domain.enums.UF;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoResponse {


    private String descricao;


    private String cep;


    private String numero;


    private String complemento;


    private String bairro;


    private String celular;


    private String cidade;


    private UF uf;
}
