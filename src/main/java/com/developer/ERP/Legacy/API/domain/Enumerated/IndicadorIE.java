package com.developer.ERP.Legacy.API.domain.Enumerated;

import lombok.Getter;

@Getter
public enum IndicadorIE {

	CONTRIBUINTE_ICMS("Contribuinte ICMS"), 
	CONTRIBUINTE_ISENTO("Contribuinte Isento"), 
	NAO_CONTRIBUINTE("Não Conribuinte");

	private String indicadores;

	private IndicadorIE(String indicadores) {
		this.indicadores = indicadores;
	}

}
