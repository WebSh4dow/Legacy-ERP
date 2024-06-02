package com.developer.ERP.Legacy.API.domain.model;

import com.developer.ERP.Legacy.API.domain.enums.TipoImovel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Imovel {
    private Long codigo;
    private Endereco endereco;
    private List<Empreendimento> empreendimentos;
    private List<Proprietario> proprietarios;
    private TipoImovel tipoImovel;
    private Date captacaoEm;
    private String codAnuncio;
    private String exclusividadeAte;
    private BigDecimal valorVenda;
    private BigDecimal valorLocalcao;
    private BigDecimal valorIptu;
    private BigDecimal valorCondominio;
    private boolean fiador;
    private boolean deposito;
    private boolean seguroFianca;
    private boolean imovelOcupado;
    private boolean inativo;
    private boolean possuiPlaca;
    private boolean exclusivo;
    private boolean lancamento;
    private boolean altoPadrao;
    private boolean temporada;
}
