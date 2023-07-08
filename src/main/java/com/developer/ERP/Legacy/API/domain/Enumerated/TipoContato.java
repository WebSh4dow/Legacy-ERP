package com.developer.ERP.Legacy.API.domain.Enumerated;

import lombok.Getter;

@Getter
public enum TipoContato {
	COMERCIAL("Comercial"), MARKETING("Marketing"), SUPORTE("Suporte"), OPERACIONAL("Operacional");

	private String tipoContato;

	TipoContato(String tipo) {
		this.tipoContato = tipo;
	}

}
