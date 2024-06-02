package com.developer.ERP.Legacy.API.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empreendimento {

    private Long codigo;
    private String descricao;
    private String porteiro;
    private String zelador;
    private Imovel imovel;
}
