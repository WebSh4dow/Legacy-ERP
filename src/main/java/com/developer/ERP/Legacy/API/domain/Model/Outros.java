package com.developer.ERP.Legacy.API.domain.Model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@ElementCollection
	private List<ReferenciasComerciais> referenciasComerciais;
}
