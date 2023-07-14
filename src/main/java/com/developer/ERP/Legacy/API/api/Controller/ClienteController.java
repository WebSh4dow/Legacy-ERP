package com.developer.ERP.Legacy.API.api.Controller;

import com.developer.ERP.Legacy.API.domain.Model.Cliente;
import com.developer.ERP.Legacy.API.domain.Model.CriteriaFilter.ClienteCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.Model.Filter.ClienteFilter;
import com.developer.ERP.Legacy.API.domain.Service.ClienteService;
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

    @GetMapping
	public ResponseEntity<Page<Cliente>> pesquisar(ClienteFilter clienteFilter,ClienteCriteriaFilter clienteCriteriaFilter) {
		return new ResponseEntity<>(clienteService.pesquisar(clienteFilter, clienteCriteriaFilter),HttpStatus.OK);
	}
    
    @PostMapping
    public Cliente salvarCliente(@RequestBody Cliente cliente) throws Exception{
    	return clienteService.cadastrarCliente(cliente);
    }
  
}
