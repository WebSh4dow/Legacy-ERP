package com.developer.ERP.Legacy.API.domain.model.administrativo.cadastros;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "COLABORADOR")
@Data
public class Colaborador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MATRICULA")
    private String matricula;

    @Column(name = "FOTO_34")
    private String foto34;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_ADMISSAO")
    private Date dataAdmissao;

    @Temporal(TemporalType.DATE)
    @Column(name = "VENCIMENTO_FERIAS")
    private Date vencimentoFerias;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_TRANSFERENCIA")
    private Date dataTransferencia;

    @Column(name = "FGTS_OPTANTE")
    private String fgtsOptante;

    @Temporal(TemporalType.DATE)
    @Column(name = "FGTS_DATA_OPCAO")
    private Date fgtsDataOpcao;

    @Column(name = "FGTS_CONTA")
    private Integer fgtsConta;

    @Column(name = "PAGAMENTO_FORMA")
    private String pagamentoForma;

    @Column(name = "PAGAMENTO_BANCO")
    private String pagamentoBanco;

    @Column(name = "PAGAMENTO_AGENCIA")
    private String pagamentoAgencia;

    @Column(name = "PAGAMENTO_AGENCIA_DIGITO")
    private String pagamentoAgenciaDigito;

    @Column(name = "PAGAMENTO_CONTA")
    private String pagamentoConta;

    @Column(name = "PAGAMENTO_CONTA_DIGITO")
    private String pagamentoContaDigito;

    @Temporal(TemporalType.DATE)
    @Column(name = "EXAME_MEDICO_ULTIMO")
    private Date exameMedicoUltimo;

    @Temporal(TemporalType.DATE)
    @Column(name = "EXAME_MEDICO_VENCIMENTO")
    private Date exameMedicoVencimento;

    @Temporal(TemporalType.DATE)
    @Column(name = "PIS_DATA_CADASTRO")
    private Date pisDataCadastro;

    @Column(name = "PIS_NUMERO")
    private String pisNumero;

    @Column(name = "PIS_BANCO")
    private String pisBanco;

    @Column(name = "PIS_AGENCIA")
    private String pisAgencia;

    @Column(name = "PIS_AGENCIA_DIGITO")
    private String pisAgenciaDigito;

    @Column(name = "CTPS_NUMERO")
    private String ctpsNumero;

    @Column(name = "CTPS_SERIE")
    private String ctpsSerie;

    @Temporal(TemporalType.DATE)
    @Column(name = "CTPS_DATA_EXPEDICAO")
    private Date ctpsDataExpedicao;

    @Column(name = "CTPS_UF")
    private String ctpsUf;

    @Column(name = "DESCONTO_PLANO_SAUDE")
    private String descontoPlanoSaude;

    @Column(name = "SAI_NA_RAIS")
    private String saiNaRais;

    @Column(name = "CATEGORIA_SEFIP")
    private String categoriaSefip;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "OCORRENCIA_SEFIP")
    private Integer ocorrenciaSefip;

    @Column(name = "CODIGO_ADMISSAO_CAGED")
    private Integer codigoAdmissaoCaged;

    @Column(name = "CODIGO_DEMISSAO_CAGED")
    private Integer codigoDemissaoCaged;

    @Column(name = "CODIGO_DEMISSAO_SEFIP")
    private Integer codigoDemissaoSefip;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_DEMISSAO")
    private Date dataDemissao;

    @Column(name = "CODIGO_TURMA_PONTO")
    private String codigoTurmaPonto;

    @Column(name = "CAGED_APRENDIZ")
    private String cagedAprendiz;

    @Column(name = "CAGED_DEFICIENCIA")
    private String cagedDeficiencia;

    @Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
    private String classificacaoContabilConta;

    @JoinColumn(name = "ID_SETOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    //@JsonManagedReference
    private Setor setor;

    @JoinColumn(name = "ID_CARGO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    //@JsonManagedReference
    private Cargo cargo;

    @JoinColumn(name = "ID_TIPO_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoColaborador tipoColaborador;

    @JoinColumn(name = "ID_NIVEL_FORMACAO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NivelFormacao nivelFormacao;

    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    @JoinColumn(name = "ID_SITUACAO_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SituacaoColaborador situacaoColaborador;

    @JoinColumn(name = "ID_TIPO_ADMISSAO", referencedColumnName = "ID")
    @ManyToOne
    private TipoAdmissao tipoAdmissao;

    @JoinColumn(name = "ID_SINDICATO", referencedColumnName = "ID")
    @ManyToOne
    private Sindicato sindicato;

}
