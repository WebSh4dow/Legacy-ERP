package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.response;

import com.developer.ERP.Legacy.API.domain.enums.TipoImovel;
import com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.request.EmpreendimentoRequest;
import com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.request.ProprietarioRequest;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.EnderecoEntity;
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
public class ImovelResponse {

    private Long codigo;
    private EnderecoEntity endereco;
    private List<EmpreendimentoResponse> empreendimentos;
    private List<ProprietarioResponse> proprietarios;
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
