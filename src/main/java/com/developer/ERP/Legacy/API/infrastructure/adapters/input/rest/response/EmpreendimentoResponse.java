package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpreendimentoResponse {
    private Long codigo;
    private String descricao;
    private String porteiro;
    private String zelador;
}
