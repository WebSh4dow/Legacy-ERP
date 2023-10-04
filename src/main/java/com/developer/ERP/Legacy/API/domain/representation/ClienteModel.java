package com.developer.ERP.Legacy.API.domain.representation;


import com.developer.ERP.Legacy.API.domain.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Relation(collectionRelation = "cliente")
public class ClienteModel extends RepresentationModel<ClienteModel> {
    private Long id;
    @NotBlank
    private String nome;
    private String sobrenome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro = LocalDate.now();
    private Outros outros;
    private PessoaJuridica pessoaJuridica;
    private PessoaFisica pessoaFisica;
    private List<Endereco> enderecos;
    private List<Contato> contatos;
    private List<Produto> produtos;
    private List<Contratos> contratos;
    private boolean isAtivo;
}
