package com.developer.ERP.Legacy.API.api.v1.controller;

import com.developer.ERP.Legacy.API.api.v1.assembler.ClienteAssembler;
import com.developer.ERP.Legacy.API.api.v1.request.ClienteRequest;
import com.developer.ERP.Legacy.API.core.data.PageWrapper;
import com.developer.ERP.Legacy.API.core.data.PageableTranslator;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.ClienteRepository;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteSpecFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;
import com.developer.ERP.Legacy.API.domain.representation.ClienteModel;
import com.developer.ERP.Legacy.API.domain.service.ClienteService;
import com.developer.ERP.Legacy.API.infrastructure.repository.ClienteSpecs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/pesquisar")
	public PagedModel<ClienteModel> pesquisar(@PageableDefault(size = 10) Pageable pageable,
			ClienteSpecFilter clienteCriteriaFilter) {

		Pageable pageableTraduzido = traduzirPageable(pageable);

		Page<Cliente> clientePage = clienteRepository.findAll(ClienteSpecs.clienteFilter(clienteCriteriaFilter),
				pageableTraduzido);

		clientePage = new PageWrapper<>(clientePage, pageable);

		return pagedResourcesAssembler.toModel(clientePage, clienteAssembler);
	}

	@GetMapping
	public PagedModel<ClienteModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cliente> clientePage = clienteRepository.findAll(pageable);
		PagedModel<ClienteModel> clientePageModel = pagedResourcesAssembler.toModel(clientePage, clienteAssembler);
		return clientePageModel;
	}
	
	@DeleteMapping("/remover/cliente/{id}")
	public void remover(@PathVariable Long id, Cliente cliente) {
		clienteService.excluir(cliente, id);
	}
	
	@PostMapping("/salvar")
	public ClienteModel salvarCliente(@RequestBody ClienteRequest cliente) {
		return clienteService.cadastrarCliente(cliente);
	}
	
	@PutMapping("/editar/{id}")
	public ClienteModel atualizar(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest){
		return clienteService.editarCliente(id,clienteRequest);
	}
	
	@GetMapping("/buscar-cliente/por-cpf/{cpf}")
	public ResponseEntity<Page<Cliente>> buscarClienteCpfPageable(ClienteFilter clienteFilter,
			ClienteSpecFilter clienteCriteriaFilter, @PathVariable String cpf) {

		Page<Cliente> buscarClientePorCpf = clienteService.buscarClienteCpfPageable(clienteFilter,
				clienteCriteriaFilter, cpf);
		return new ResponseEntity<Page<Cliente>>(buscarClientePorCpf, HttpStatus.OK);
	}
	
	@GetMapping("/buscar-cliente/por-cnpj/{cnpj}")
	public ResponseEntity<Page<Cliente>> buscarClienteCnpjPageable(ClienteFilter clienteFilter,
			ClienteSpecFilter clienteCriteriaFilter, @PathVariable String cnpj) {

		Page<Cliente> buscarClientePorCpf = clienteService.buscarClienteCnpjPageable(clienteFilter,
				clienteCriteriaFilter, cnpj);
		return new ResponseEntity<Page<Cliente>>(buscarClientePorCpf, HttpStatus.OK);
	}
	
	@GetMapping("/buscar-cliente/{id}")
	public ResponseEntity<Page<Cliente>> buscarClientePorId(ClienteFilter clienteFilter,
			ClienteSpecFilter clienteCriteriaFilter, @PathVariable Long id) {
		Page<Cliente> buscarClientesPorId = clienteService.buscarClientesPorIdPageable(clienteFilter,
				clienteCriteriaFilter, id);
		return new ResponseEntity<Page<Cliente>>(buscarClientesPorId, HttpStatus.OK);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"id", "id",
				"nome", "nome",
				"sobreNome", "sobreNome",
				"outros", "outros",
				"pessoaFisica", "pessoaFisica",
				"pessoaJuridica", "pessoaJuridica"
			);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}
