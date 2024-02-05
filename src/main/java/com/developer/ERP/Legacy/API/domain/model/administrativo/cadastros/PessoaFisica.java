package com.developer.ERP.Legacy.API.domain.model.administrativo.cadastros;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "PESSOA_FISICA")
@Data
public class PessoaFisica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "RG")
    private String rg;

    @Column(name = "ORGAO_RG")
    private String orgaoRg;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_EMISSAO_RG")
    private Date dataEmissaoRg;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "NATURALIDADE")
    private String naturalidade;

    @Column(name = "NACIONALIDADE")
    private String nacionalidade;

    @Column(name = "RACA")
    private String raca;

    @Column(name = "TIPO_SANGUE")
    private String tipoSangue;

    @Column(name = "CNH_NUMERO")
    private String cnhNumero;

    @Column(name = "CNH_CATEGORIA")
    private String cnhCategoria;

    @Temporal(TemporalType.DATE)
    @Column(name = "CNH_VENCIMENTO")
    private Date cnhVencimento;

    @Column(name = "TITULO_ELEITORAL_NUMERO")
    private String tituloEleitoralNumero;

    @Column(name = "TITULO_ELEITORAL_ZONA")
    private Integer tituloEleitoralZona;

    @Column(name = "TITULO_ELEITORAL_SECAO")
    private Integer tituloEleitoralSecao;

    @Column(name = "RESERVISTA_NUMERO")
    private String reservistaNumero;

    @Column(name = "RESERVISTA_CATEGORIA")
    private Integer reservistaCategoria;

    @Column(name = "NOME_MAE")
    private String nomeMae;

    @Column(name = "NOME_PAI")
    private String nomePai;

    @JoinColumn(name = "ID_ESTADO_CIVIL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EstadoCivil estadoCivil;

    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Pessoa pessoa;


}
