package com.developer.ERP.Legacy.API.domain.model;

import com.developer.ERP.Legacy.API.domain.enums.Genero;
import com.developer.ERP.Legacy.API.domain.enums.Nacionalidade;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ContaCorrenteEntity;
import lombok.*;

import javax.persistence.Column;
import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proprietario {

    private Long codigo;
    private String nome;
    private String cpf;
    private String gerenciadoPor;
    private String rg;
    private String dataNascimento;
    private String profissao;
    private String natura;
    private Nacionalidade nacionalidade;
    private Honorario honorario;
    private Conjugue conjugue;
    private DadosPessoais dadosPessoais;
    private Endereco endereco;
    private Genero genero;
    private List<ContaCorrente> contasBancarias;
    private List<Imovel> imoveis;
    private List<Imovel> historicoImovel;

}
