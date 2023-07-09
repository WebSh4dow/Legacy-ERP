package com.developer.ERP.Legacy.API.domain.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Max(value = 500)
	@Min(value = 10)
	private String historico;
	
	private String nome;
	
	private String sobrenome;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataNascimento;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataCadastro = LocalDate.now();

	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "outros_id")
	private Outros outros;

	@ManyToOne
	@JoinColumn(name = "pessoaJuridica_id")
	private PessoaJuridica pessoaJuridica;
	
	@ManyToOne
	@JoinColumn(name = "pessoaFisica_id")
	private PessoaFisica pessoaFisica;

	@ElementCollection
	private List<Endereco> enderecos;

	@ElementCollection
	private List<Contato> contatos;
	
	@ElementCollection
	private List<Produto> produtos;
	
	@ElementCollection
	private List<Contratos> contratos;
	
	private boolean isAtivo;

}
