package com.developer.ERP.Legacy.API.domain.model;

import com.developer.ERP.Legacy.API.domain.enumerated.RegimeTributacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Max(value = 500)
	@Min(value = 10)
	private String historico;

	private String nome;

	private String sobreNome;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro = LocalDate.now();

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "outros_id")
	private Outros outros;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "pessoaJuridica_id")
	@Valid
	private PessoaJuridica pessoaJuridica;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "pessoaFisica_id")
	@Valid
	private PessoaFisica pessoaFisica;

	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "cliente_id")
	private List<Endereco> enderecos;

	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "cliente_id")
	private List<Contato> contatos;

	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@Column(name = "contrato_id")
	@JoinColumn(name = "cliente_id")
	private List<Contratos> contratos;
	
	@Enumerated(EnumType.STRING)
	private RegimeTributacao regimeTributacao;

	private boolean isAtivo;
	


}
