package com.developer.ERP.Legacy.API.domain.Model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.developer.ERP.Legacy.API.domain.Enumerated.Estado;
import com.developer.ERP.Legacy.API.domain.Enumerated.TipoContato;
import lombok.Data;

@Entity
@Data
public class Contato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String telefone;

	private String email;

	private String complemento;
	
	private boolean emailNfse;

	@Enumerated(EnumType.STRING)
	private Estado estado;

	@Enumerated(EnumType.STRING)
	private TipoContato tipoContato;

}
