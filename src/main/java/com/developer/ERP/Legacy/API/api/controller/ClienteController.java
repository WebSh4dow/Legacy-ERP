package com.developer.ERP.Legacy.API.api.controller;

import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;
import com.developer.ERP.Legacy.API.domain.service.ClienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
	public ResponseEntity<Page<Cliente>> pesquisar(ClienteFilter clienteFilter,ClienteCriteriaFilter clienteCriteriaFilter) {
		return new ResponseEntity<>(clienteService.pesquisar(clienteFilter, clienteCriteriaFilter),HttpStatus.OK);
	}
    
    @PostMapping
    public Cliente salvarCliente(@RequestBody Cliente cliente) throws Exception{
    	return clienteService.cadastrarCliente(cliente);
    }
    @GetMapping("contratos-vinculados/{id}")
    public Page<Cliente>buscarContratosClienteByCriteriaFilterPaginator(ClienteCriteriaFilter clienteFilter, Pageable pageable, @PathVariable Long id){
    	return clienteService.buscarContratosClienteByCriteriaFilterPaginator(clienteFilter, pageable);
    }
    
    
  
}
