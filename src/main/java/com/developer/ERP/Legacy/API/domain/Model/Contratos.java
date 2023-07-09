package com.developer.ERP.Legacy.API.domain.Model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.developer.ERP.Legacy.API.domain.Enumerated.CentroCusto;
import com.developer.ERP.Legacy.API.domain.Enumerated.TipoContrato;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Entity
@Data
public class Contratos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private boolean status;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataInicial;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataVencimento;
	
	@Enumerated(EnumType.STRING)
	private CentroCusto centroCusto;
	
	@Enumerated(EnumType.STRING)
	private TipoContrato tipoContratos;
	
	
}
