package com.developer.ERP.Legacy.API.domain.representation;

import com.developer.ERP.Legacy.API.domain.model.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ClienteRepresentationModel {
    private Long id;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
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
