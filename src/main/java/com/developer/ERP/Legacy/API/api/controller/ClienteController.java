package com.developer.ERP.Legacy.API.api.controller;

import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;
import com.developer.ERP.Legacy.API.domain.service.ClienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/pesquisar")
	public ResponseEntity<Page<Cliente>> pesquisar(ClienteFilter clienteFilter,ClienteCriteriaFilter clienteCriteriaFilter) {
		return new ResponseEntity<>(clienteService.pesquisar(clienteFilter, clienteCriteriaFilter), HttpStatus.OK);
	}

	@PostMapping("/salvar")
	public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) throws Exception {
		return new ResponseEntity<Cliente>(clienteService.cadastrarCliente(cliente), HttpStatus.OK);
	}
	
	@GetMapping("/buscar-cliente/por-cpf/{cpf}")
	public Page<Cliente> buscarClienteCpfPageable(ClienteFilter clienteFilter,
			ClienteCriteriaFilter clienteCriteriaFilter, @PathVariable String cpf) {
		return clienteService.buscarClienteCpfPageable(clienteFilter, clienteCriteriaFilter, cpf);
	}
	@GetMapping("/buscar-cliente/por-cnpj/{cnpj}")
	public Page<Cliente> buscarClienteCnpjPageable(ClienteFilter clienteFilter,
			ClienteCriteriaFilter clienteCriteriaFilter, @PathVariable String cnpj) {
		return clienteService.buscarClienteCnpjPageable(clienteFilter, clienteCriteriaFilter, cnpj);
	}


	

}
