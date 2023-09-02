package com.developer.ERP.Legacy.API.api.v1.controller;

import com.developer.ERP.Legacy.API.domain.model.Endereco;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.EnderecoCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.EnderecoFilter;
import com.developer.ERP.Legacy.API.domain.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("api/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<Page<Endereco>> listar(EnderecoFilter enderecoFilter,
			EnderecoCriteriaFilter enderecoCriteriaFilter) {
		return new ResponseEntity<>(enderecoService.listarTodosEnderecos(enderecoFilter, enderecoCriteriaFilter),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> listarPor(@PathVariable Long id) {
		return enderecoService.listarPor(id);
	}

	@PostMapping
	public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {
		return enderecoService.salvar(endereco);
	}

	@PutMapping("/{id}")
	public Endereco editar(@PathVariable Long id, @RequestBody Endereco endereco) {
		return enderecoService.editar(endereco, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> remover(@PathVariable Long id) {
		return enderecoService.remover(id);
	}

}
