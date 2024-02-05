package com.developer.ERP.Legacy.API.domain.model.administrativo.cadastros;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTADOR")
@Data
public class Contador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "INSCRICAO_CRC")
    private String inscricaoCrc;

    @Column(name = "UF_CRC")
    private String ufCrc;

    @Column(name = "FONE")
    private String fone;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "MUNICIPIO_IBGE")
    private Integer municipioIbge;

    @Column(name = "UF")
    private String uf;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SITE")
    private String site;
}
