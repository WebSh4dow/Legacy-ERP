package com.developer.ERP.Legacy.API.api.Controller;

import com.developer.ERP.Legacy.API.domain.Model.Cliente;
import com.developer.ERP.Legacy.API.domain.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @GetMapping
    public List <Cliente>listAll() {
        return clienteService.listAll();
    }
    @PostMapping
    public ResponseEntity <Cliente> salvarCliente(@RequestBody Cliente cliente){
        return clienteService.saveCliente(cliente);
    }
    @GetMapping("/{id}")
    public Cliente editar(@RequestBody Cliente cliente, @PathVariable Long id){
        return clienteService.editar(cliente, id);
    }
    @PutMapping("/{id}")
    public ResponseEntity <Cliente> porId(@PathVariable Long id){
        return clienteService.findByCliente(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> remover(@PathVariable Long id){
        return clienteService.remover(id);
    }
}
