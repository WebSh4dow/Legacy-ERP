package com.developer.ERP.Legacy.API.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import com.developer.ERP.Legacy.API.core.annotations.Cnpj;
import com.developer.ERP.Legacy.API.core.annotations.UniqueKeyValidator;
import com.developer.ERP.Legacy.API.domain.enumerated.CentroCusto;
import com.developer.ERP.Legacy.API.domain.enumerated.IndicadorIE;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@UniqueKeyValidator(nomePropiedades = "cnpj",message = "O CNPJ Não pode ser usado pois já existe um cadastro com o mesmo cnpj")
public class PessoaJuridica implements Serializable {
	private static final long serialversionUID = 129348938L;
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

	@Cnpj
	@NotBlank
	private String cnpj;

	@Enumerated(EnumType.STRING)
	private IndicadorIE indicadorIe;

	@Enumerated(EnumType.STRING)
	private CentroCusto centroCusto;

}
