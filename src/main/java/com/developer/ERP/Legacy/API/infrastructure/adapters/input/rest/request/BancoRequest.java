package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BancoRequest {
    @NotBlank(message = "Descrição é um campo obrigatório")
    private String descricao;
}
