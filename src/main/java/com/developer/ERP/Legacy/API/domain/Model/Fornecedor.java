package com.developer.ERP.Legacy.API.domain.Model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pessoaTipo;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String cpf;
    private String rg;
    private String contaCorrente;
    private String observacoes;
    private LocalDate dataCadastro = LocalDate.now();
    private String representante;
    private String telefone;
    private String telefone2;
    private String fundacao;
    private String criacao;
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}
