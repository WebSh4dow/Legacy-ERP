package com.developer.ERP.Legacy.API.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.developer.ERP.Legacy.API.core.annotations.Cnpj;
import com.developer.ERP.Legacy.API.domain.enumerated.CentroCusto;
import com.developer.ERP.Legacy.API.domain.enumerated.IndicadorIE;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaJuridica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String razaoSocial;

	@NotBlank
	private String nomeFantasia;

	@NotBlank
	private String inscricaoEstadual;

	@NotBlank
	private String inscricaoMunicipal;

	@NotBlank
	@Cnpj
	private String cnpj;

	@Enumerated(EnumType.STRING)
	private IndicadorIE indicadorIe;

	@Enumerated(EnumType.STRING)
	private CentroCusto centroCusto;

}
