package com.developer.ERP.Legacy.API.domain.model.administrativo.cadastros;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FPAS")
@Data
public class Fpas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODIGO")
    private Integer codigo;

    @Column(name = "CNAE")
    private String cnae;

    @Column(name = "ALIQUOTA_SAT")
    private BigDecimal aliquotaSat;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PERCENTUAL_INSS_PATRONAL")
    private BigDecimal percentualInssPatronal;

    @Column(name = "CODIGO_TERCEIRO")
    private String codigoTerceiro;

    @Column(name = "PERCENTUAL_TERCEIROS")
    private BigDecimal percentualTerceiros;

}
