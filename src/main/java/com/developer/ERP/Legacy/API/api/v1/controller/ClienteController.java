package com.developer.ERP.Legacy.API.api.v1.controller;

import com.developer.ERP.Legacy.API.api.v1.request.ClienteRequest;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;
import com.developer.ERP.Legacy.API.domain.representation.ClienteRepresentationModel;
import com.developer.ERP.Legacy.API.domain.service.ClienteService;
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
	public ResponseEntity<Page<Cliente>> pesquisar(ClienteFilter clienteFilter,
			ClienteCriteriaFilter clienteCriteriaFilter) {
		return new ResponseEntity<>(clienteService.pesquisar(clienteFilter, clienteCriteriaFilter), HttpStatus.OK);
	}
	@DeleteMapping("/remover/cliente/{id}")
	public void remover(@PathVariable Long id, Cliente cliente) {
		clienteService.excluir(cliente, id);
	}
	@PostMapping("/salvar")
	public ClienteRepresentationModel salvarCliente(@RequestBody ClienteRequest cliente) {
		return clienteService.cadastrarCliente(cliente);
	}
	@PutMapping("/editar/{id}")
	public ClienteRepresentationModel atualizar(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest){
		return clienteService.editarCliente(id,clienteRequest);
	}
	@GetMapping("/buscar-cliente/por-cpf/{cpf}")
	public ResponseEntity<Page<Cliente>> buscarClienteCpfPageable(ClienteFilter clienteFilter,
			ClienteCriteriaFilter clienteCriteriaFilter, @PathVariable String cpf) {

		Page<Cliente> buscarClientePorCpf = clienteService.buscarClienteCpfPageable(clienteFilter,
				clienteCriteriaFilter, cpf);
		return new ResponseEntity<Page<Cliente>>(buscarClientePorCpf, HttpStatus.OK);
	}
	@GetMapping("/buscar-cliente/por-cnpj/{cnpj}")
	public ResponseEntity<Page<Cliente>> buscarClienteCnpjPageable(ClienteFilter clienteFilter,
			ClienteCriteriaFilter clienteCriteriaFilter, @PathVariable String cnpj) {

		Page<Cliente> buscarClientePorCpf = clienteService.buscarClienteCnpjPageable(clienteFilter,
				clienteCriteriaFilter, cnpj);
		return new ResponseEntity<Page<Cliente>>(buscarClientePorCpf, HttpStatus.OK);
	}
	@GetMapping("/buscar-cliente/{id}")
	public ResponseEntity<Page<Cliente>> buscarClientePorId(ClienteFilter clienteFilter,
			ClienteCriteriaFilter clienteCriteriaFilter, @PathVariable Long id) {
		Page<Cliente> buscarClientesPorId = clienteService.buscarClientesPorIdPageable(clienteFilter,
				clienteCriteriaFilter, id);
		return new ResponseEntity<Page<Cliente>>(buscarClientesPorId, HttpStatus.OK);
	}

}
