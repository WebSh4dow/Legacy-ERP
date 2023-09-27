package com.developer.ERP.Legacy.API.domain.enumerated;

import lombok.Getter;

@Getter
public enum RegimeTributacao {
	PESSOA_FISICA(0L,"0- Pessoa Fisica"),
	SIMPLES_NACIONAL(1L,"1- Simples Nacional."),
	REGIME_NORMAL_LUCRO_REAL(2L,"2- Regime Normal - Lucro Presumido."),
	REGIME_NORMAL_LUCRO_PRESUMIDO(3L,"3- Regime Normal - Lucro Real."),
	REGIME_SEM_FINS_LUCRATIVOS (4L," 4- Empresa sem fins lucrativos.");
	
	private String regimeTributacao;
	private Long identificadorRegime;

	RegimeTributacao(Long identificadorRegime, String regimeTributacao) {
		this.identificadorRegime = identificadorRegime;
		this.regimeTributacao = regimeTributacao;
	}
}
