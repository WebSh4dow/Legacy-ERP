package com.developer.ERP.Legacy.API.domain.model.administrativo.cadastros;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "EMPRESA")
@Data
public class Empresa {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "INSCRICAO_ESTADUAL")
    private String inscricaoEstadual;

    @Column(name = "INSCRICAO_ESTADUAL_ST")
    private String inscricaoEstadualSt;

    @Column(name = "INSCRICAO_MUNICIPAL")
    private String inscricaoMunicipal;

    @Column(name = "INSCRICAO_JUNTA_COMERCIAL")
    private String inscricaoJuntaComercial;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INSC_JUNTA_COMERCIAL")
    private Date dataInscJuntaComercial;

    @Column(name = "TIPO")
    private String tipo;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INICIO_ATIVIDADES")
    private Date dataInicioAtividades;

    @Column(name = "SUFRAMA")
    private String suframa;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IMAGEM_LOGOTIPO")
    private String imagemLogotipo;

    @Column(name = "CRT")
    private String crt;

    @Column(name = "TIPO_REGIME")
    private String tipoRegime;

    @Column(name = "ALIQUOTA_PIS")
    private BigDecimal aliquotaPis;

    @Column(name = "CONTATO")
    private String contato;

    @Column(name = "ALIQUOTA_COFINS")
    private BigDecimal aliquotaCofins;

    @Column(name = "CODIGO_IBGE_CIDADE")
    private Integer codigoIbgeCidade;

    @Column(name = "CODIGO_IBGE_UF")
    private Integer codigoIbgeUf;

    @Column(name = "CODIGO_TERCEIROS")
    private Integer codigoTerceiros;

    @Column(name = "CODIGO_GPS")
    private Integer codigoGps;

    @Column(name = "ALIQUOTA_SAT")
    private BigDecimal aliquotaSat;

    @Column(name = "CEI")
    private String cei;

    @Column(name = "CODIGO_CNAE_PRINCIPAL")
    private String codigoCnaePrincipal;

    @Column(name = "TIPO_CONTROLE_ESTOQUE")
    private String tipoControleEstoque;

    @JoinColumn(name = "ID_CONTADOR", referencedColumnName = "ID")
    @ManyToOne
    private Contador contador;

    @JoinColumn(name = "ID_FPAS", referencedColumnName = "ID")
    @ManyToOne
    private Fpas fpas;

    @JoinColumn(name = "ID_SINDICATO_PATRONAL", referencedColumnName = "ID")
    @ManyToOne
    private Sindicato sindicato;

    @OneToMany(mappedBy="empresa", fetch = FetchType.EAGER)
    private Set<EmpresaEndereco> listaEndereco;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy = "listaEmpresa")
    private List<Pessoa> listaPessoa;

    @Transient
    private byte[] imagem;
}
