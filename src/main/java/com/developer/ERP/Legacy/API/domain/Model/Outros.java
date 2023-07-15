package com.developer.ERP.Legacy.API.domain.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Outros {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String condicoesPagamentos;
	
	private String formaPagamento;
	
	private String inscricaoSuframa;
	
	private String banco;
	
	private String agencia;
	
	private String contaCorrente;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "outros_id")
	private List<ReferenciasComerciais> referenciasComerciais;
}
