package com.developer.ERP.Legacy.API.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pessoaFisjuridica;
    private String nome;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dataNascimento;
    private String cpf_Cnpj;
    private String rg;
    private String telefone1;
    private String telefone2;
    private String naturalidade;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataCadastro = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;





}
