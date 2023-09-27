package com.developer.ERP.Legacy.API.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.developer.ERP.Legacy.API.core.annotations.Cpf;
import com.developer.ERP.Legacy.API.domain.enumerated.CentroCusto;
import com.developer.ERP.Legacy.API.domain.enumerated.IndicadorIE;

import lombok.Data;

@Entity
@Data
public class PessoaFisica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	@Cpf
	private String cpf;
	
	private String rg;
	
	private String inscricaoEstadual;

	private String inscricaoMunicipal;
	
	@Enumerated(EnumType.STRING)
	private IndicadorIE indicadorIe;
	
	@Enumerated(EnumType.STRING)
	private CentroCusto centroCusto;
	
	
	
	
}
