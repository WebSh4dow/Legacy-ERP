package com.developer.ERP.Legacy.API.api.Controller;

import com.developer.ERP.Legacy.API.domain.Model.Fornecedor;
import com.developer.ERP.Legacy.API.domain.Service.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController("api/fornecedores")
public class FornecedorController {
    private FornecedorService fornecedorService;
    public FornecedorService fornecedorService(FornecedorService fornecedorService){
        return this.fornecedorService = fornecedorService;
    }
    @GetMapping
    public List <Fornecedor>list() {
        return fornecedorService.listarFornecedor();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor>porId(@PathVariable Long id) {
        return fornecedorService.porId(id);
    }
    @PostMapping
    public ResponseEntity<Fornecedor> adcionar(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.salvarFornecedor(fornecedor);
    }
    @PutMapping("/{id}")
    public Fornecedor editar(@PathVariable Long id, @RequestBody Fornecedor fornecedor){
        return fornecedorService.editar(fornecedor,id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> remover(@PathVariable Long id){
        return fornecedorService.remover(id);
    }

}
