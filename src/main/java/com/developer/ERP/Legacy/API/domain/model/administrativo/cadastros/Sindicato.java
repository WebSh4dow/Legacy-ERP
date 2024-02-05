package com.developer.ERP.Legacy.API.domain.model.administrativo.cadastros;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SINDICATO")
@Data
public class Sindicato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CODIGO_BANCO")
    private Integer codigoBanco;

    @Column(name = "CODIGO_AGENCIA")
    private Integer codigoAgencia;

    @Column(name = "CONTA_BANCO")
    private String contaBanco;

    @Column(name = "CODIGO_CEDENTE")
    private String codigoCedente;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "MUNICIPIO_IBGE")
    private Integer municipioIbge;

    @Column(name = "UF")
    private String uf;

    @Column(name = "FONE1")
    private String fone1;

    @Column(name = "FONE2")
    private String fone2;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TIPO_SINDICATO")
    private String tipoSindicato;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_BASE")
    private Date dataBase;

    @Column(name = "PISO_SALARIAL")
    private BigDecimal pisoSalarial;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
    private String classificacaoContabilConta;
}
