package com.developer.ERP.Legacy.API.core.abstraction;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
@Getter
@Setter
public class AbstractService <T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T objetoSelecionado;
    private T objeto;
    public HashMap<String, String> simNao;
    public HashMap<String, String> tipoPessoa;
    public HashMap<String, String> sexo;
    public HashMap<String, String> tipoSangue;
    public HashMap<String, String> racaCor;
    public HashMap<String, String> crt;
    public HashMap<String, String> tipoRegimeEmpresa;
    public HashMap<String, Integer> tipoTelefone;
    public HashMap<String, String> clienteIndicadorPreco;
    public HashMap<String, String> clienteTipoFrete;
    public HashMap<String, String> clienteFormaDesconto;
    public HashMap<String, String> fornecedorLocalizacao;
    public HashMap<String, String> colaboradorFormaPagamento;
    public HashMap<String, String> tipoSindicato;
    public HashMap<String, String> tipoItemSped;
    public HashMap<String, String> produtoClasse;
    public HashMap<String, String> produtoTipo;
    public HashMap<String, String> produtoIat;
    public HashMap<String, String> produtoIppt;
    public HashMap<String, String> talonarioChequeStatus;
    public HashMap<String, String> chequeStatus;
    public HashMap<String, String> tipoContaCaixa;
    public HashMap<String, String> tipoFeriado;
    public HashMap<String, String> feriadoAbrangencia;
    public HashMap<String, String> codigoModeloNfe;
    public HashMap<String, Integer> localDestinoNfe;
    public HashMap<String, Integer> consumidorOperacaoNfe;
    public HashMap<String, Integer> consumidorPresencaNfe;
    public HashMap<String, Integer> tipoOperacaoNfe;
    public HashMap<String, Integer> tipoEmissaoNfe;
    public HashMap<String, Integer> finalidadeEmissaoNfe;
    public HashMap<String, Integer> formatoImpressaoDanfeNfe;
    public HashMap<String, Integer> indicadorFormaPagamentoNfe;
    public HashMap<String, Integer> statusNfe;
    public HashMap<String, Integer> modalidadeFreteNfe;
    public HashMap<String, Integer> origemMercadoriaNfe;
    public HashMap<String, String> compraSituacaoCotacao;
    public HashMap<String, String> compraFormaPagamento;
    public HashMap<String, String> compraTipoFrete;
    public HashMap<String, String> tipoNaturazaFinanceira;
    public HashMap<String, String> layoutRemessa;
    public HashMap<String, String> especieCobranca;
    public HashMap<String, String> tipoBaixa;
    public HashMap<String, String> metodoDepreciacao;
    public HashMap<String, String> tipoDepreciacao;
    public HashMap<String, String> pontoTratamentoHoraMais;
    public HashMap<String, String> pontoTratamentoHoramenos;
    public HashMap<String, String> tipoHorarioPonto;
    public HashMap<String, String> tipoTrabalho;
    public HashMap<String, String> utilizacaoRelogioPonto;
    public HashMap<String, String> situacaoBancoHoras;
    public HashMap<String, String> tipoRegistroPonto;
    public HashMap<String, String> pontoModalidadeHoraExtra;
    public HashMap<String, String> pontoHoraCompensar;
    public HashMap<String, String> origemMercadoria;
    public HashMap<String, String> tributIcmsBaseCalculo;
    public HashMap<String, String> tributIcmsStBaseCalculo;
    public HashMap<String, String> pisModalidadeBaseCalculo;
    public HashMap<String, String> issModalidadeBaseCalculo;
    public HashMap<String, String> issCodigoTributacao;
    public HashMap<String, String> informarContaContabil;
    public HashMap<String, String> periodicidadeInciceContabil;
    public HashMap<String, String> tipoPlanoContaSped;
    public HashMap<String, String> situacaoContaContabil;
    public HashMap<String, String> naturezaContaContabil;
    public HashMap<String, String> patrimonioResultadoContaContabil;
    public HashMap<String, String> dfcContaContabil;
    public HashMap<String, String> criterioLancamentoFechamento;
    public HashMap<String, String> tipoLancamentoProgramado;
    public HashMap<String, String> tipoLancamento;
    public HashMap<String, String> formaCalculoDre;
    public HashMap<String, String> sinalDre;
    public HashMap<String, String> contabilLivroFormaEscrituracao;
    public HashMap<String, String> aberturaFechamento;
    public HashMap<String, String> fiscalParametrosApuracao;
    public HashMap<String, String> fiscalParametrosCalculoPisCofins;
    public HashMap<String, String> simplesNacionalCabecalho;
    public HashMap<String, String> fiscalParametrosSimplesAtividade;
    public HashMap<String, String> spedPerfil;
    public HashMap<String, String> fiscalParametrosFormaCalculoIss;
    public HashMap<String, String> aberturaEncerramento;
    public HashMap<String, String> requisicaoInternaSituacao;
    public HashMap<String, String> tipoReajuste;
    public HashMap<String, String> contratoStatusSolicitacaoServico;
    public HashMap<String, String> vendaOrcamentoTipo;
    public HashMap<String, String> vendaOrcamentoSituacao;
    public HashMap<String, String> formaPagamento;
    public HashMap<String, String> VendaResponsavelFrete;
    public HashMap<String, String> vendaRomaneioSituacao;
    public HashMap<String, String> tipoContagem;
    public HashMap<String, String> formaPagamentoComissao;
    public HashMap<String, String> ecdFormaEscrituracao;
    public HashMap<String, String> ecdVersaoLayout;
    public HashMap<String, String> categoriaCompromissoCor;
    public HashMap<String, Integer> compromissoTipo;
    public HashMap<String, Integer> compromissoRecorrente;
    public HashMap<String, Integer> formatoCodigoBarra;
    public HashMap<String, Integer> osTipoCobertura;
    public HashMap<String, Integer> osTipoProdutoServico;
    public HashMap<String, String> nfseTipoRPS;
    public HashMap<String, String> nfseNaturezaOperacao;
    public HashMap<String, String> nfseRegimeEspecialTributacao;
    public HashMap<String, Integer> cteFormatoImpressaoDacte;
    public HashMap<String, Integer> cteTipoPeriodo;
    public HashMap<String, String> cteCodigoUnidadeMedida;
    public HashMap<String, Integer> cteFormaPagamento;
    public HashMap<String, Integer> cteTipo;
    public HashMap<String, Integer> cteTipoServico;
    public HashMap<String, Integer> cteTomador;
    public HashMap<String, String> cteModal;
    public HashMap<String, Integer> cteTipoEmissao;

    public AbstractService() {
        initializeMaps();
    }

    private void initializeMaps() {

        tipoPessoa = new HashMap<>();
        tipoPessoa.put("Fisica", "F");
        tipoPessoa.put("Jurídica", "J");

        simNao = new HashMap<>();
        simNao.put("Sim", "S");
        simNao.put("Não", "N");

        sexo = new HashMap<>();
        sexo.put("Masculino", "M");
        sexo.put("Feminino", "F");

        tipoSangue = new HashMap<>();
        tipoSangue.put("A+", "A+");
        tipoSangue.put("B+", "B+");
        tipoSangue.put("O+", "O+");
        tipoSangue.put("AB+", "AB+");
        tipoSangue.put("A-", "A-");
        tipoSangue.put("B-", "B-");
        tipoSangue.put("AB-", "AB-");
        tipoSangue.put("O-", "O-");

        racaCor = new HashMap<>();
        racaCor.put("Branco", "B");
        racaCor.put("Negro", "N");
        racaCor.put("Pardo", "P");
        racaCor.put("Indio", "I");

        crt = new HashMap<>();
        crt.put("1 - Simples Nacional", "1");
        crt.put("2 - Simples Nac - Excesso", "2");
        crt.put("3 - Regime Normal", "3");

        tipoRegimeEmpresa = new HashMap<>();
        tipoRegimeEmpresa.put("Lucro Real", "1");
        tipoRegimeEmpresa.put("Lucro Presumido", "2");
        tipoRegimeEmpresa.put("Simples Nacional", "3");

        tipoTelefone = new HashMap<>();
        tipoTelefone.put("Residencial", 0);
        tipoTelefone.put("Comercial", 1);
        tipoTelefone.put("Celular", 2);
        tipoTelefone.put("Outro", 3);

        clienteIndicadorPreco = new HashMap<>();
        clienteIndicadorPreco.put("Tabela", "T");
        clienteIndicadorPreco.put("Último Pedido", "P");

        clienteTipoFrete = new HashMap<>();
        clienteTipoFrete.put("Emitente", "E");
        clienteTipoFrete.put("Destinatario", "D");
        clienteTipoFrete.put("Sem frete", "S");
        clienteTipoFrete.put("Terceiros", "T");

        clienteFormaDesconto = new HashMap<>();
        clienteFormaDesconto.put("Produto", "P");
        clienteFormaDesconto.put("Fim do Pedido", "F");

        fornecedorLocalizacao = new HashMap<>();
        fornecedorLocalizacao.put("Nacional", "N");
        fornecedorLocalizacao.put("Exterior", "E");

        colaboradorFormaPagamento = new HashMap<>();
        colaboradorFormaPagamento.put("Dinheiro", "1");
        colaboradorFormaPagamento.put("Cheque", "2");
        colaboradorFormaPagamento.put("Conta", "3");

        tipoSindicato = new HashMap<>();
        tipoSindicato.put("Empregados", "E");
        tipoSindicato.put("Patronal", "P");

        tipoItemSped = new HashMap<>();
        tipoItemSped.put("Mercadoria para Revenda", "00");
        tipoItemSped.put("Matéria-Prima", "01");
        tipoItemSped.put("Embalagem", "02");
        tipoItemSped.put("Produto em Processo", "03");
        tipoItemSped.put("Produto Acabado", "04");
        tipoItemSped.put("Subproduto", "05");
        tipoItemSped.put("Produto Intermediário", "06");
        tipoItemSped.put("Material de Uso e Consumo", "07");
        tipoItemSped.put("Ativo Imobilizado", "08");
        tipoItemSped.put("Serviços", "09");
        tipoItemSped.put("Outros Insumos", "10");
        tipoItemSped.put("Outras", "99");

        produtoClasse = new HashMap<>();
        produtoClasse.put("A", "A");
        produtoClasse.put("B", "B");
        produtoClasse.put("C", "C");

        produtoTipo = new HashMap<>();
        produtoTipo.put("Venda", "V");
        produtoTipo.put("Composição", "C");
        produtoTipo.put("Produção", "P");
        produtoTipo.put("Insumo", "I");
        produtoTipo.put("Uso Proprio", "U");

        produtoIat = new HashMap<>();
        produtoIat.put("Arredondamento", "A");
        produtoIat.put("Truncamento", "T");

        produtoIppt = new HashMap<>();
        produtoIppt.put("Próprio", "P");
        produtoIppt.put("Terceiro", "T");

        talonarioChequeStatus = new HashMap<>();
        talonarioChequeStatus.put("Normal", "N");
        talonarioChequeStatus.put("Cancelado", "C");
        talonarioChequeStatus.put("Extraviado", "E");
        talonarioChequeStatus.put("Utilizado", "U");

        chequeStatus = new HashMap<>();
        chequeStatus.put("Em Ser", "E");
        chequeStatus.put("Baixado", "B");
        chequeStatus.put("Utilizado", "U");
        chequeStatus.put("Compensado", "C");
        chequeStatus.put("Cancelado", "N");

        tipoContaCaixa = new HashMap<>();
        tipoContaCaixa.put("Corrente", "C");
        tipoContaCaixa.put("Poupança", "P");
        tipoContaCaixa.put("Investimento", "I");
        tipoContaCaixa.put("Caixa Interno", "X");

        tipoFeriado = new HashMap<>();
        tipoFeriado.put("Fixo", "F");
        tipoFeriado.put("Móvel", "M");

        feriadoAbrangencia = new HashMap<>();
        feriadoAbrangencia.put("Federal", "F");
        feriadoAbrangencia.put("Estadual", "E");
        feriadoAbrangencia.put("Municipal", "M");

        codigoModeloNfe = new HashMap<>();
        codigoModeloNfe.put("Nota Fiscal Eletrônica - NFe", "55");

        localDestinoNfe = new HashMap<>();
        localDestinoNfe.put("Operação Interna", 1);
        localDestinoNfe.put("Operação Interestadual", 2);
        localDestinoNfe.put("Operação com Exterior", 3);

        consumidorOperacaoNfe = new HashMap<>();
        consumidorOperacaoNfe.put("Normal", 0);
        consumidorOperacaoNfe.put("Consumidor Final", 1);

        consumidorPresencaNfe = new HashMap<>();
        consumidorPresencaNfe.put("Operação Presencial", 1);
        consumidorPresencaNfe.put("Operação não Presencial - Internet", 2);
        consumidorPresencaNfe.put("Operação não Presencial - Teleatendimento", 3);
        consumidorPresencaNfe.put("Operação não Presencial - Outros", 9);
        consumidorPresencaNfe.put("Não se aplica", 0);

        tipoOperacaoNfe = new HashMap<>();
        tipoOperacaoNfe.put("Entrada", 0);
        tipoOperacaoNfe.put("Saída", 1);

        tipoEmissaoNfe = new HashMap<>();
        tipoEmissaoNfe.put("Normal", 1);
        tipoEmissaoNfe.put("Contigência", 2);
        tipoEmissaoNfe.put("Contingência SCAN", 3);
        tipoEmissaoNfe.put("Contingência DPEC", 4);
        tipoEmissaoNfe.put("Contingência FS-DA", 5);

        finalidadeEmissaoNfe = new HashMap<>();
        finalidadeEmissaoNfe.put("Normal", 1);
        finalidadeEmissaoNfe.put("Complementar", 2);
        finalidadeEmissaoNfe.put("Ajuste", 3);

        formatoImpressaoDanfeNfe = new HashMap<>();
        formatoImpressaoDanfeNfe.put("Retrato", 1);
        formatoImpressaoDanfeNfe.put("Paisagem", 1);

        indicadorFormaPagamentoNfe = new HashMap<>();
        indicadorFormaPagamentoNfe.put("A Vista", 0);
        indicadorFormaPagamentoNfe.put("A Prazo", 1);
        indicadorFormaPagamentoNfe.put("Outros", 2);

        statusNfe = new HashMap<>();
        statusNfe.put("Em Edição", 0);
        statusNfe.put("Salva", 1);
        statusNfe.put("Validada", 2);
        statusNfe.put("Assinada", 3);
        statusNfe.put("Enviada", 4);
        statusNfe.put("Autorizada", 5);
        statusNfe.put("Cancelada", 6);

        modalidadeFreteNfe = new HashMap<>();
        modalidadeFreteNfe.put("Conta Emitente", 0);
        modalidadeFreteNfe.put("Conta Destinatário", 1);
        modalidadeFreteNfe.put("Conta Terceiros", 2);
        modalidadeFreteNfe.put("Sem Frete", 9);

        origemMercadoriaNfe = new HashMap<>();
        origemMercadoriaNfe.put("Nacional", 0);
        origemMercadoriaNfe.put("Estrangeira - Importação direta", 1);
        origemMercadoriaNfe.put("Estrangeira - Adquirida no mercado interno", 2);

        compraSituacaoCotacao = new HashMap<>();
        compraSituacaoCotacao.put("Aberta", "A");
        compraSituacaoCotacao.put("Confirmada", "C");
        compraSituacaoCotacao.put("Fechada", "F");

        compraFormaPagamento = new HashMap<>();
        compraFormaPagamento.put("A Vista", "0");
        compraFormaPagamento.put("A Prazo", "1");
        compraFormaPagamento.put("Outros", "2");

        compraTipoFrete = new HashMap<>();
        compraTipoFrete.put("CIF", "C");
        compraTipoFrete.put("FOB", "F");

        tipoNaturazaFinanceira  = new HashMap<>();
        tipoNaturazaFinanceira.put("Receita", "R");
        tipoNaturazaFinanceira.put("Despesa", "D");

        layoutRemessa = new HashMap<>();
        layoutRemessa.put("240", "240");
        layoutRemessa.put("400", "400");

        especieCobranca = new HashMap<>();
        especieCobranca.put("Duplicata Mercantil", "DM");
        especieCobranca.put("Duplicata de Serviços", "DS");
        especieCobranca.put("Recibo", "RC");
        especieCobranca.put("Nota Promissória", "NP");

        tipoBaixa = new HashMap<>();
        tipoBaixa.put("Total", "T");
        tipoBaixa.put("Parcial", "P");

        metodoDepreciacao = new HashMap<>();
        metodoDepreciacao.put("Linear", "1");
        metodoDepreciacao.put("Soma dos Algarismos dos Anos", "2");
        metodoDepreciacao.put("Horas de Trabalho", "3");
        metodoDepreciacao.put("Unidades Produzidas", "4");

        tipoDepreciacao = new HashMap<>();
        tipoDepreciacao.put("Normal", "N");
        tipoDepreciacao.put("Acelerada", "A");
        tipoDepreciacao.put("Incentivada", "I");

        pontoTratamentoHoraMais = new HashMap<>();
        pontoTratamentoHoraMais.put("Banco de Horas", "B");
        pontoTratamentoHoraMais.put("Pagar como Extra", "E");

        pontoTratamentoHoramenos = new HashMap<>();
        pontoTratamentoHoramenos.put("Banco de Horas", "B");
        pontoTratamentoHoramenos.put("Descontar como Falta", "F");

        tipoHorarioPonto = new HashMap<>();
        tipoHorarioPonto.put("Fixo", "F");
        tipoHorarioPonto.put("Diário", "D");
        tipoHorarioPonto.put("Semanal", "S");
        tipoHorarioPonto.put("Mensal", "M");

        tipoTrabalho = new HashMap<>();
        tipoTrabalho.put("Normal", "N");
        tipoTrabalho.put("Compensação", "C");
        tipoTrabalho.put("Folga", "F");

        utilizacaoRelogioPonto = new HashMap<>();
        utilizacaoRelogioPonto.put("Ponto", "P");
        utilizacaoRelogioPonto.put("Refeitório", "R");
        utilizacaoRelogioPonto.put("Circulação", "C");

        situacaoBancoHoras = new HashMap<>();
        situacaoBancoHoras.put("Não Utilizado", "N");
        situacaoBancoHoras.put("Utilizado", "U");
        situacaoBancoHoras.put("Utilizado Parcialmente", "P");

        tipoRegistroPonto = new HashMap<>();
        tipoRegistroPonto.put("Original", "O");
        tipoRegistroPonto.put("Incluído por Digitação", "I");
        tipoRegistroPonto.put("Intervalo Pré-assinalado", "P");

        pontoModalidadeHoraExtra = new HashMap<>();
        pontoModalidadeHoraExtra.put("Diurna", "D");
        pontoModalidadeHoraExtra.put("Noturna", "N");

        pontoHoraCompensar = new HashMap<>();
        pontoModalidadeHoraExtra.put("Horas a mais", "1");
        pontoModalidadeHoraExtra.put("Horas a Menos", "2");

        origemMercadoria = new HashMap<>();
        origemMercadoria.put("Nacional", "0");
        origemMercadoria.put("Estrangeira - Importação direta", "1");
        origemMercadoria.put("Estrangeira - Adquirida no mercado interno", "2");

        tributIcmsBaseCalculo = new HashMap<>();
        tributIcmsBaseCalculo.put("Margem Valor Agregado (%)", "0");
        tributIcmsBaseCalculo.put("Pauta (Valor)", "1");
        tributIcmsBaseCalculo.put("Preço Tabelado Máx. (valor)", "2");
        tributIcmsBaseCalculo.put("Valor da Operação", "3");

        tributIcmsStBaseCalculo = new HashMap<>();
        tributIcmsStBaseCalculo.put("Preço tabelado ou máximo sugerido", "0");
        tributIcmsStBaseCalculo.put("Lista Negativa (valor)", "1");
        tributIcmsStBaseCalculo.put("Lista Positiva (valor)", "2");
        tributIcmsStBaseCalculo.put("Lista Neutra (valor)", "3");
        tributIcmsStBaseCalculo.put("Margem Valor Agregado(%)", "4");
        tributIcmsStBaseCalculo.put("Pauta (valor)", "5");

        pisModalidadeBaseCalculo = new HashMap<>();
        pisModalidadeBaseCalculo.put("Percentual", "0");
        pisModalidadeBaseCalculo.put("Unidade", "0");

        issModalidadeBaseCalculo = new HashMap<>();
        issModalidadeBaseCalculo.put("Valor Operação", "0");
        issModalidadeBaseCalculo.put("Outros", "9");

        issCodigoTributacao = new HashMap<>();
        issCodigoTributacao.put("Normal", "N");
        issCodigoTributacao.put("Retida", "R");
        issCodigoTributacao.put("Substituta", "S");
        issCodigoTributacao.put("Isenta", "I");

        informarContaContabil = new HashMap<>();
        informarContaContabil.put("Código", "C");
        informarContaContabil.put("Máscara", "M");

        periodicidadeInciceContabil = new HashMap<>();
        periodicidadeInciceContabil.put("Diário", "D");
        periodicidadeInciceContabil.put("Mensal", "M");

        tipoPlanoContaSped = new HashMap<>();
        tipoPlanoContaSped.put("Sintética", "S");
        tipoPlanoContaSped.put("Análitica", "A");

        situacaoContaContabil = new HashMap<>();
        situacaoContaContabil.put("Ativa", "A");
        situacaoContaContabil.put("Inativa", "I");

        naturezaContaContabil = new HashMap<>();
        naturezaContaContabil.put("Credora", "C");
        naturezaContaContabil.put("Devedora", "D");

        patrimonioResultadoContaContabil = new HashMap<>();
        patrimonioResultadoContaContabil.put("Patrimônio", "P");
        patrimonioResultadoContaContabil.put("Resultado", "R");

        dfcContaContabil = new HashMap<>();
        dfcContaContabil.put("Não participa", "N");
        dfcContaContabil.put("Atividades Operacionais", "O");
        dfcContaContabil.put("Atividades de Financiamento", "F");
        dfcContaContabil.put("Atividades de Investimento", "I");

        criterioLancamentoFechamento = new HashMap<>();
        criterioLancamentoFechamento.put("Livre", "L");
        criterioLancamentoFechamento.put("Avisar", "A");
        criterioLancamentoFechamento.put("Não permitir (para lançamentos efetuados fora do período informado)", "N");

        tipoLancamentoProgramado = new HashMap<>();
        tipoLancamentoProgramado.put("Um Débito para Vários Créditos", "UDVC");
        tipoLancamentoProgramado.put("Um Crédito para Vários Débitos", "UCVD");
        tipoLancamentoProgramado.put("Um Débito para Um Crédito", "UDUC");
        tipoLancamentoProgramado.put("Vários Débitos para Vários Créditos", "VDVC");

        tipoLancamento = new HashMap<>();
        tipoLancamento.put("Crédito", "C");
        tipoLancamento.put("Débito", "D");

        formaCalculoDre = new HashMap<>();
        formaCalculoDre.put("Sintética [soma contas filhas - sinal de mais ou de menos]", "S");
        formaCalculoDre.put("Vinculada [vinculada a conta do balancete - recupera o sinal da conta mãe]", "V");
        formaCalculoDre.put("Resultado de Operações da DRE [soma das operações - sinal de igual]", "R");

        sinalDre = new HashMap<>();
        sinalDre.put("+", "+");
        sinalDre.put("-", "-");
        sinalDre.put("=", "=");

        contabilLivroFormaEscrituracao = new HashMap<>();
        contabilLivroFormaEscrituracao.put("Diário Geral", "G");
        contabilLivroFormaEscrituracao.put("Diário com Escrituração Resumida", "R");
        contabilLivroFormaEscrituracao.put("Diário Auxiliar", "A");
        contabilLivroFormaEscrituracao.put("Razão Auxiliar", "Z");
        contabilLivroFormaEscrituracao.put("Livro de Balancetes Diários e Balanços", "B");

        aberturaFechamento = new HashMap<>();
        aberturaFechamento.put("Abertura", "A");
        aberturaFechamento.put("Fechamento", "F");

        fiscalParametrosApuracao = new HashMap<>();
        fiscalParametrosApuracao.put("Regime Competencia", "1");
        fiscalParametrosApuracao.put("Regime de Caixa", "2");

        fiscalParametrosCalculoPisCofins = new HashMap<>();
        fiscalParametrosCalculoPisCofins.put("Alíquota Básica", "AB");
        fiscalParametrosCalculoPisCofins.put("Alíquota Diferenciada", "AD");
        fiscalParametrosCalculoPisCofins.put("Unidade de Medida de Produto", "UP");

        simplesNacionalCabecalho = new HashMap<>();
        simplesNacionalCabecalho.put("Federal", "1");
        simplesNacionalCabecalho.put("Estadual", "2");

        fiscalParametrosSimplesAtividade = new HashMap<>();
        fiscalParametrosSimplesAtividade.put("Comercio", "CO");
        fiscalParametrosSimplesAtividade.put("Indústria", "IN");
        fiscalParametrosSimplesAtividade.put("Serviços Anexo III", "S1");
        fiscalParametrosSimplesAtividade.put("Serviços Anexo IV", "S2");
        fiscalParametrosSimplesAtividade.put("Serviços Anexo V", "S3");

        spedPerfil = new HashMap<>();
        spedPerfil.put("A", "A");
        spedPerfil.put("B", "B");
        spedPerfil.put("C", "C");

        fiscalParametrosFormaCalculoIss = new HashMap<>();
        fiscalParametrosFormaCalculoIss.put("Normal", "NO");
        fiscalParametrosFormaCalculoIss.put("Profissional Habilitado", "PH");
        fiscalParametrosFormaCalculoIss.put("Valor Fixo", "VF");

        aberturaEncerramento = new HashMap<>();
        aberturaEncerramento.put("Abertura", "A");
        aberturaEncerramento.put("Encerramento", "E");

        requisicaoInternaSituacao = new HashMap<>();
        requisicaoInternaSituacao.put("Aberta", "A");
        requisicaoInternaSituacao.put("Deferida", "D");
        requisicaoInternaSituacao.put("Indeferida", "I");

        tipoReajuste = new HashMap<>();
        tipoReajuste.put("Aumentar", "A");
        tipoReajuste.put("Diminuir", "D");

        contratoStatusSolicitacaoServico = new HashMap<>();
        contratoStatusSolicitacaoServico.put("Aguardando", "A");
        contratoStatusSolicitacaoServico.put("Deferido", "D");
        contratoStatusSolicitacaoServico.put("Indeferido", "I");

        vendaOrcamentoTipo = new HashMap<>();
        vendaOrcamentoTipo.put("Orçamento", "O");
        vendaOrcamentoTipo.put("Pedido", "P");

        vendaOrcamentoSituacao = new HashMap<>();
        vendaOrcamentoSituacao.put("Digitacao", "D");
        vendaOrcamentoSituacao.put("Producao", "P");
        vendaOrcamentoSituacao.put("Expedicao", "X");
        vendaOrcamentoSituacao.put("Faturado", "F");
        vendaOrcamentoSituacao.put("Entregue", "E");

        formaPagamento = new HashMap<>();
        formaPagamento.put("0 - A Vista", "0");
        formaPagamento.put("1 - A Prazo", "1");
        formaPagamento.put("2 - Outros", "2");

        VendaResponsavelFrete = new HashMap<>();
        VendaResponsavelFrete.put("Emitente", "1");
        VendaResponsavelFrete.put("Destinatário", "2");

        vendaRomaneioSituacao = new HashMap<>();
        vendaRomaneioSituacao.put("Aberto", "A");
        vendaRomaneioSituacao.put("Trânsito", "T");
        vendaRomaneioSituacao.put("Encerrado", "E");

        tipoContagem = new HashMap<>();
        tipoContagem.put("Geral", "G");
        tipoContagem.put("Dinâmico", "D");
        tipoContagem.put("Rotativo", "R");
        tipoContagem.put("Amostragem", "A");

        formaPagamentoComissao = new HashMap<>();
        formaPagamentoComissao.put("Fixo", "0");
        formaPagamentoComissao.put("Percentual", "1");

        ecdFormaEscrituracao = new HashMap<>();
        ecdFormaEscrituracao.put("Diário Geral", "G");
        ecdFormaEscrituracao.put("Diário com Escrituração Resumida (vinculado a livro auxiliar)", "R");
        ecdFormaEscrituracao.put("Diário Auxiliar", "A");
        ecdFormaEscrituracao.put("Razão Auxiliar", "Z");
        ecdFormaEscrituracao.put("Livro de Balancetes Diários e Balanços", "B");

        ecdVersaoLayout = new HashMap<>();
        ecdVersaoLayout.put("1.00", "1.00");
        ecdVersaoLayout.put("2.00", "2.00");

        categoriaCompromissoCor = new HashMap<>();
        categoriaCompromissoCor.put("Amarelo", "Amarelo");
        categoriaCompromissoCor.put("Azul", "Azul");
        categoriaCompromissoCor.put("Branco", "Branco");
        categoriaCompromissoCor.put("Verde", "Verde");
        categoriaCompromissoCor.put("Vermelho", "Vermelho");

        compromissoTipo = new HashMap<>();
        compromissoTipo.put("Pessoal", 0);
        compromissoTipo.put("Gerencial", 1);

        compromissoRecorrente = new HashMap<>();
        compromissoRecorrente.put("Não", 0);
        compromissoRecorrente.put("Diário", 1);
        compromissoRecorrente.put("Semanal", 2);
        compromissoRecorrente.put("Mensal", 3);
        compromissoRecorrente.put("Anual", 4);

        formatoCodigoBarra = new HashMap<>();
        formatoCodigoBarra.put("EAN", 0);
        formatoCodigoBarra.put("QR Code", 1);

        osTipoCobertura = new HashMap<>();
        osTipoCobertura.put("NENHUM", 0);
        osTipoCobertura.put("GARANTIA", 1);
        osTipoCobertura.put("SEGURO", 2);
        osTipoCobertura.put("CONTRATO", 3);

        osTipoProdutoServico = new HashMap<>();
        osTipoProdutoServico.put("PRODUTO", 0);
        osTipoProdutoServico.put("SERVIÇO", 1);

        nfseTipoRPS = new HashMap<>();
        nfseTipoRPS.put("Recibo Provisório de Serviços", "1");
        nfseTipoRPS.put("RPS Nota Fiscal Conjugada (Mista)", "2");
        nfseTipoRPS.put("Cupom", "3");

        nfseNaturezaOperacao = new HashMap<>();
        nfseNaturezaOperacao.put("Tributação no município", "1");
        nfseNaturezaOperacao.put("Tributação fora do município", "2");
        nfseNaturezaOperacao.put("Isenção", "3");
        nfseNaturezaOperacao.put("Imune", "4");
        nfseNaturezaOperacao.put("Exigibilidade suspensa por decisão judicial", "5");
        nfseNaturezaOperacao.put("Exigibilidade suspensa por procedimento administrativo", "6");

        nfseRegimeEspecialTributacao = new HashMap<>();
        nfseRegimeEspecialTributacao.put("Microempresa Municipal", "1");
        nfseRegimeEspecialTributacao.put("Estimativa", "2");
        nfseRegimeEspecialTributacao.put("Sociedade de Profissionais", "3");
        nfseRegimeEspecialTributacao.put("Cooperativa", "4");

        cteFormatoImpressaoDacte = new HashMap<>();
        cteFormatoImpressaoDacte.put("Retrato", 1);
        cteFormatoImpressaoDacte.put("Paisagem", 2);

        cteTipoPeriodo = new HashMap<>();
        cteTipoPeriodo.put("Sem data definida", 0);
        cteTipoPeriodo.put("Na data", 1);
        cteTipoPeriodo.put("Até a data", 2);
        cteTipoPeriodo.put("A partir da data", 3);
        cteTipoPeriodo.put("No período", 4);

        cteCodigoUnidadeMedida = new HashMap<>();
        cteCodigoUnidadeMedida.put("M3", "00");
        cteCodigoUnidadeMedida.put("KG", "01");
        cteCodigoUnidadeMedida.put("TON", "02");
        cteCodigoUnidadeMedida.put("UNIDADE", "03");
        cteCodigoUnidadeMedida.put("LITROS", "04");
        cteCodigoUnidadeMedida.put("MMBTU", "05");

        cteFormaPagamento = new HashMap<>();
        cteFormaPagamento.put("Pago", 0);
        cteFormaPagamento.put("A pagar", 1);
        cteFormaPagamento.put("Outros", 2);

        cteTipo = new HashMap<>();
        cteTipo.put("CT-e Normal", 0);

        cteTipoServico = new HashMap<>();
        cteTipoServico.put("Normal", 0);
        cteTipoServico.put("Subcontratação", 1);
        cteTipoServico.put("Redespacho", 2);
        cteTipoServico.put("Redespacho  Intermediário", 3);
        cteTipoServico.put("Serviço Vinculado a  Multimodal", 4);

        cteTomador = new HashMap<>();
        cteTomador.put("Remetente", 0);
        cteTomador.put("Expedidor", 1);
        cteTomador.put("Recebedor", 2);
        cteTomador.put("Destinatário", 3);
        cteTomador.put("Outros", 4);

        cteModal = new HashMap<>();
        cteModal.put("Rodoviário", "01");
        cteModal.put("Aéreo", "02");
        cteModal.put("Aquaviário", "03");
        cteModal.put("Ferroviário", "04");
        cteModal.put("Dutoviário", "05");
        cteModal.put("Multimodal", "06");

        cteTipoEmissao = new HashMap<>();
        cteTipoEmissao.put("Normal", 1);
        cteTipoEmissao.put("EPEC pela SVC", 4);
        cteTipoEmissao.put("Contingência FSDA", 5);
        cteTipoEmissao.put("Autorização pela SVC-RS", 7);
        cteTipoEmissao.put("Autorização pela SVC-SP", 8);

    }
}
