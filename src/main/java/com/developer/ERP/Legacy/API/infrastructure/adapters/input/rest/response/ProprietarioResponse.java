package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.response;

import com.developer.ERP.Legacy.API.domain.enums.Genero;
import com.developer.ERP.Legacy.API.domain.enums.Nacionalidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProprietarioResponse  {

    private Long codigo;

    private String nome;

    private String cpf;

    private String gerenciadoPor;

    private String rg;

    private String dataNascimento;

    private String profissao;

    private String natura;

    private Nacionalidade nacionalidade;

    private HonorarioResponse honorario;

    private DadosPessoaisResponse dadosPessoais;

    private EnderecoResponse endereco;

    private Genero genero;
}
