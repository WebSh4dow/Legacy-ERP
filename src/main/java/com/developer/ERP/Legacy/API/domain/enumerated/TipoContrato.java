package com.developer.ERP.Legacy.API.domain.enumerated;

import lombok.Getter;

@Getter
public enum TipoContrato {
	CONTRATO_SOCIAL("Contrato Social."),
	CONTRATO_COMPRA_E_VENDA("Contrato de compra e venda."),
	CONTRATO_ADMINISTRATIVO("Contrato administrativo."),
	CONTRATO_DE_CONSUMO("Contrato de consumo."),
	CONTRATO_DE_SOCIEDADE("Contrato de sociedade."),
	CONTRATO_DE_MERCANTIS("Contrato de mercantis."),
	CONTRATO_DE_PRESTACAO_SERVICO("Contrato de prestação de serviço."),
	CONTRATO_ELETRONICO("Contrato Eletrônico."),
	CONTRATO_DE_TRABALHO("Contrato de trabalho.");
	
	private String tipoContrato;
	
	TipoContrato(String tipo) {
		this.tipoContrato = tipo;
	}
}
