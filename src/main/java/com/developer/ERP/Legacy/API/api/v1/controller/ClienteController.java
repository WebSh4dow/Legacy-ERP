package com.developer.ERP.Legacy.API.api.v1.controller;

import com.developer.ERP.Legacy.API.ApiLinks;
import com.developer.ERP.Legacy.API.api.v1.assembler.ClienteAssembler;
import com.developer.ERP.Legacy.API.api.v1.request.ClienteRequest;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.BussinesException;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.ClienteRepository;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteSpecFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;
import com.developer.ERP.Legacy.API.domain.repository.specs.ClienteSpecification;
import com.developer.ERP.Legacy.API.domain.representation.ClienteModel;
import com.developer.ERP.Legacy.API.domain.service.ClienteService;
import com.developer.ERP.Legacy.API.domain.service.ContratoService;
import com.developer.ERP.Legacy.API.infrastructure.specification.PesquisarCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@Autowired
	private ContratoService contratoService;

	@Autowired
	private ApiLinks apiLinks;

	private static final String PARAMS_CRITERIO_EXCEPTION = "Criterio de pesquisa atual necessita que o " +
			"parametro atual [contrato_id], [cliente_id] " +
			"e [pesquisarContratos] estejam corretamentes preenchidos";
	private static final Integer PESQUISAR = 1;

	@GetMapping
	public ResponseEntity<CollectionModel<ClienteModel>> listarClientes() {
		List<Cliente> clientes = clienteService.buscarTodosCliente();
		CollectionModel<ClienteModel> collectionModel = clienteAssembler.toCollectionModel(clientes);
		return ResponseEntity.ok().body(collectionModel);
	}
	
	@PostMapping("/filtrar")
	public PagedModel<ClienteModel> filtrar(@RequestBody PesquisarCriteria<ClienteSpecFilter> clienteSpecFilter){
		ClienteSpecification clienteSpecification = new ClienteSpecification(clienteSpecFilter);
		Page<Cliente> page = clienteRepository.findAll(clienteSpecification, clienteSpecification.getPageable());
		return pagedResourcesAssembler.toModel(page,clienteAssembler);
	}

	@GetMapping("/buscar/contratos/{idContrato}/{idCliente}")
	public List<Cliente> listarContratosVinculados(@PathVariable Long idCliente,  @PathVariable Long idContrato){
		return contratoService.buscarContrato(idContrato,idCliente);
	}

	@GetMapping("/pesquisar")
	public CollectionModel<ClienteModel> pesquisar(
			@PageableDefault(size = 10) Pageable pageable,
			@RequestParam (required = false,name = "contrato_id")  Long contrato_id,
			@RequestParam (required = false,name = "cliente_id") Long cliente_id,
			@RequestParam (required = false) Integer pesquisarContratos) {

		if (pesquisarContratos == null && cliente_id != null  || contrato_id == null){
			throw new BussinesException(PARAMS_CRITERIO_EXCEPTION);
		}

		if (pesquisarContratos.equals(PESQUISAR)) {
			List<Cliente> todosClientes  = contratoService.buscarContrato(contrato_id,cliente_id);
			return clienteAssembler.toCollectionModel(todosClientes);
		}

		List<Cliente> todosClientes  = clienteService.buscarTodosCliente();
		return clienteAssembler.toCollectionModel(todosClientes);
	}

	@GetMapping("/buscar-cliente/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id){
		Cliente buscarClientesPorId = clienteService.buscarCliente(id);
		return new ResponseEntity<>(buscarClientesPorId,HttpStatus.OK);
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
	
	@GetMapping("/buscar-cliente/paginado/{id}")
	public ResponseEntity<Page<Cliente>> buscarClientePorId(ClienteFilter clienteFilter,
			ClienteSpecFilter clienteCriteriaFilter, @PathVariable Long id) {
		Page<Cliente> buscarClientesPorId = clienteService.buscarClientesPorIdPageable(clienteFilter,
				clienteCriteriaFilter, id);
		return new ResponseEntity<Page<Cliente>>(buscarClientesPorId, HttpStatus.OK);
	}
}
