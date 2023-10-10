package com.developer.ERP.Legacy.API.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import com.developer.ERP.Legacy.API.core.annotations.Cpf;
import com.developer.ERP.Legacy.API.core.annotations.UniqueKeyValidator;
import com.developer.ERP.Legacy.API.domain.enumerated.CentroCusto;
import com.developer.ERP.Legacy.API.domain.enumerated.IndicadorIE;
import lombok.Data;
import java.io.Serializable;

@Entity
@Data
@UniqueKeyValidator(nomePropiedades = "cpf")

public class PessoaFisica implements Serializable {

	private static final long serialversionUID = 129348938L;
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
