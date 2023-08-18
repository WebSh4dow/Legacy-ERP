package com.developer.ERP.Legacy.API.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	private String nome;
	
	private String marca;
	
	private Integer estoqueFisico;
	
	private Integer estoqueMinimo;
	
	private BigDecimal precoDeCusto;
	
	private BigDecimal precoDeCustoTotal;
	
	private BigDecimal precoAvista;
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	@ManyToOne
	@JoinColumn(name = "transportadora_id")
	private Transportadora transportadora;

}
