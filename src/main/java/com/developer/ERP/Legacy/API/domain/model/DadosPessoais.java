package com.developer.ERP.Legacy.API.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosPessoais {

    private Long codigo;

    private String email;

    private String recados;
}
