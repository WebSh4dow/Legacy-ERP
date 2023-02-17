package com.developer.ERP.Legacy.API.api.Controller;

import com.developer.ERP.Legacy.API.domain.Model.Cliente;
import com.developer.ERP.Legacy.API.domain.Service.ClienteRelatorioService;
import com.developer.ERP.Legacy.API.domain.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRelatorioService clienteRelatorioService;
    @GetMapping
    public List <Cliente>listAll() {
        return clienteService.listAll();
    }
    @GetMapping("/report")
    public ResponseEntity<InputStreamResource> report() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=clientes.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(this.clienteRelatorioService.relatorios()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
