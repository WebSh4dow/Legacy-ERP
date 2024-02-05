package com.developer.ERP.Legacy.API.domain.model.administrativo.cadastros;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "NIVEL_FORMACAO")
@Data
public class NivelFormacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "GRAU_INSTRUCAO_CAGED")
    private Integer grauInstrucaoCaged;

    @Column(name = "GRAU_INSTRUCAO_SEFIP")
    private Integer grauInstrucaoSefip;

    @Column(name = "GRAU_INSTRUCAO_RAIS")
    private Integer grauInstrucaoRais;
}
