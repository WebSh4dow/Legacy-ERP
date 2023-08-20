package com.developer.ERP.Legacy.API.domain.repository.criteriaFilter;

import java.time.LocalDate;

import com.developer.ERP.Legacy.API.domain.enumerated.CentroCusto;
import com.developer.ERP.Legacy.API.domain.enumerated.TipoContrato;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ContratoCriteriaFilter {

	private Long codigoContrato;

	private boolean status;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicial;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;

	private CentroCusto centroCusto;

	private TipoContrato tipoContratos;

	private Cliente cliente;

}
