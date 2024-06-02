package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpreendimentoRequest {
    private Long codigo;
    private String descricao;
    private String porteiro;
    private String zelador;
    private ImovelRequest imovel;
}
