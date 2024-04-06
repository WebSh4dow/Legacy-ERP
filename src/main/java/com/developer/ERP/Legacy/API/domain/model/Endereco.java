package com.developer.ERP.Legacy.API.domain.model;

import com.developer.ERP.Legacy.API.domain.enums.UF;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {

    private Long codigo;

    private String descricao;

    private String cep;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private UF uf;
}
