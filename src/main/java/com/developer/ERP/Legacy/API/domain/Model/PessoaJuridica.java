package com.developer.ERP.Legacy.API.domain.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.developer.ERP.Legacy.API.domain.Enumerated.CentroCusto;
import com.developer.ERP.Legacy.API.domain.Enumerated.IndicadorIE;

import lombok.Data;

@Entity
@Data
public class PessoaJuridica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String razaoSocial;

	private String nomeFantasia;

	private String inscricaoEstadual;

	private String inscricaoMunicipal;

	private String cnpj;
	
	@Enumerated(EnumType.STRING)
	private IndicadorIE indicadorIe;
	
	@Enumerated(EnumType.STRING)
	private CentroCusto centroCusto;

}
