package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.response;

import com.developer.ERP.Legacy.API.domain.enums.Genero;
import com.developer.ERP.Legacy.API.domain.enums.Nacionalidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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

    private ConjugueResponse conjugue;

    private DadosPessoaisResponse dadosPessoais;

    private EnderecoResponse endereco;

    private List<ImovelResponse> imoveis;

    private List<ImovelResponse> historicoImoveis;

    private List<ContaCorrenteResponse> contasBancarias;

    private Genero genero;
}
