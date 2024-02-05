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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "PESSOA_JURIDICA")
@Data
public class PessoaJuridica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "FANTASIA")
    private String fantasia;

    @Column(name = "INSCRICAO_MUNICIPAL")
    private String inscricaoMunicipal;

    @Column(name = "INSCRICAO_ESTADUAL")
    private String inscricaoEstadual;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CONSTITUICAO")
    private Date dataConstituicao;

    @Column(name = "TIPO_REGIME")
    private String tipoRegime;

    @Column(name = "CRT")
    private String crt;

    @Column(name = "SUFRAMA")
    private String suframa;

    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private Pessoa pessoa;

}
