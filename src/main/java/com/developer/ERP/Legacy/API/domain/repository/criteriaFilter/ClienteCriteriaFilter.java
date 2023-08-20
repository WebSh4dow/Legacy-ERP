package com.developer.ERP.Legacy.API.domain.repository.criteriaFilter;

import java.util.List;

import com.developer.ERP.Legacy.API.domain.model.Contratos;
import com.developer.ERP.Legacy.API.domain.model.Endereco;
import com.developer.ERP.Legacy.API.domain.model.PessoaFisica;
import com.developer.ERP.Legacy.API.domain.model.PessoaJuridica;

import lombok.Data;

@Data
public class ClienteCriteriaFilter {
	private Long id;
	private String nome;
	private String sobreNome;
	private String historico;
	private PessoaFisica pessoaFisica;
	private PessoaJuridica pessoaJuridica;
	private List <Contratos> contratos;
	private List<Endereco> enderecos;
	private boolean isAtivo;
}
