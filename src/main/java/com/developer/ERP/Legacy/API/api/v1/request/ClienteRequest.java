package com.developer.ERP.Legacy.API.api.v1.request;

import com.developer.ERP.Legacy.API.core.annotations.Cpf;
import com.developer.ERP.Legacy.API.domain.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ClienteRequest {

    @Max(value = 500)
    @Min(value = 10)
    private String historico;
    @NotBlank
    private String nome;

    private String sobrenome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

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
