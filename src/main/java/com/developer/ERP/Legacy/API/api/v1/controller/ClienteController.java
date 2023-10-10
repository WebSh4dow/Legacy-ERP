package com.developer.ERP.Legacy.API.api.v1.controller;

import com.developer.ERP.Legacy.API.api.v1.assembler.ClienteAssembler;
import com.developer.ERP.Legacy.API.api.v1.request.ClienteRequest;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.ClienteRepository;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteSpecFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;
import com.developer.ERP.Legacy.API.domain.repository.specs.ClienteSpecification;
import com.developer.ERP.Legacy.API.domain.representation.ClienteModel;
import com.developer.ERP.Legacy.API.domain.service.ClienteService;
import com.developer.ERP.Legacy.API.infrastructure.specification.PesquisarCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PagedResourcesAssembler<Cliente> pagedResourcesAssembler;
	
	@Autowired
	private ClienteAssembler clienteAssembler;
	
	@PostMapping("/filtrar")
	public PagedModel<ClienteModel> filtrar(@RequestBody PesquisarCriteria<ClienteSpecFilter> clienteSpecFilter){
		ClienteSpecification clienteSpecification = new ClienteSpecification(clienteSpecFilter);
		Page<Cliente> page = clienteRepository.findAll(clienteSpecification, clienteSpecification.getPageable());
		return pagedResourcesAssembler.toModel(page,clienteAssembler);
	}

	@GetMapping("/pesquisar")
	public PagedModel<ClienteModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cliente> clientePage = clienteRepository.findAll(pageable);
		return pagedResourcesAssembler.toModel(clientePage, clienteAssembler);
	}
	
	@DeleteMapping("/remover/cliente/{id}")
	public void remover(@PathVariable Long id, Cliente cliente) {
		clienteService.excluir(cliente, id);
	}
	
	@PostMapping("/salvar")
	public ClienteModel salvarCliente(@Validated @RequestBody ClienteRequest cliente) {
		return clienteService.cadastrarCliente(cliente);
	}
	
	@PutMapping("/editar/{id}")
	public ClienteModel atualizar(@PathVariable Long id, @RequestBody @Validated ClienteRequest clienteRequest){
		return clienteService.editarCliente(id,clienteRequest);
	}
	
	@GetMapping("/buscar-cliente/{id}")
	public ResponseEntity<Page<Cliente>> buscarClientePorId(ClienteFilter clienteFilter,
			ClienteSpecFilter clienteCriteriaFilter, @PathVariable Long id) {
		Page<Cliente> buscarClientesPorId = clienteService.buscarClientesPorIdPageable(clienteFilter,
				clienteCriteriaFilter, id);
		return new ResponseEntity<Page<Cliente>>(buscarClientesPorId, HttpStatus.OK);
	}

}
