package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosPessoaisResponse {

    private String email;

    private String recados;
}
