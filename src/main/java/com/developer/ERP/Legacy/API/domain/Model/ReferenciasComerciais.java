package com.developer.ERP.Legacy.API.domain.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ReferenciasComerciais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeFantasia;
	
	private String nomeContato;
	
	private String telefone;
	
	private String email;
}
