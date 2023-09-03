package com.developer.ERP.Legacy.API.api.v1.request;

import com.developer.ERP.Legacy.API.domain.enumerated.CentroCusto;
import com.developer.ERP.Legacy.API.domain.enumerated.IndicadorIE;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PessoaJuridicaRequest {
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String cnpj;
    private IndicadorIE indicadorIe;
    private CentroCusto centroCusto;
}
