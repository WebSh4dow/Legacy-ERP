package com.developer.ERP.Legacy.API.domain.enumerated;

import lombok.Getter;

@Getter
public enum CentroCusto {

	CENTRO_DE_CUSTO_PRODUTIVO("Centro de custo produtivo"),
	CENTRO_DE_CUSTO_NAO_PRODUTIVO("Centro de custo não produtivo");
	
	private String tipoCentroCusto;
	
	private CentroCusto(String centroCusto) {
		this.tipoCentroCusto = centroCusto;
	}
}
