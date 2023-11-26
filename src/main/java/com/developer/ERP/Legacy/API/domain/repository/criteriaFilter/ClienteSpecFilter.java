package com.developer.ERP.Legacy.API.domain.repository.criteriaFilter;

import java.util.List;
import com.developer.ERP.Legacy.API.domain.enumerated.RegimeTributacao;
import com.developer.ERP.Legacy.API.domain.model.Contratos;
import com.developer.ERP.Legacy.API.domain.model.Endereco;
import lombok.Data;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class ClienteSpecFilter {
	private Long id;
	private String nome;
	private String sobreNome;
	private String historico;
	private String cpf;
	private String cnpj;
	private List<Endereco> enderecos;
	@Enumerated(EnumType.STRING)
	private RegimeTributacao regimeTributacao;
	private boolean isAtivo;
}
