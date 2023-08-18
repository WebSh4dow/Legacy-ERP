package com.developer.ERP.Legacy.API.api.controller;

import com.developer.ERP.Legacy.API.domain.model.Produto;
import com.developer.ERP.Legacy.API.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/produtos")
public class produtoController {
    @Autowired
    private ProdutoService produtoService;
    @GetMapping
    public List <Produto>listarTodos(){
        return produtoService.todos();
    }
    @GetMapping("/{id}")
    public ResponseEntity <Produto>listarPor(@PathVariable Long id){
        return produtoService.pesquisarPor(id);
    }
    @PostMapping
    public ResponseEntity <Produto> salvar(@RequestBody Produto produtos){
        return produtoService.salvar(produtos);
    }
    @PutMapping("/{id}")
    public Produto editar(@PathVariable Long id,@RequestBody Produto produto){
        return produtoService.editarProdutos(id, produto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> remover(@PathVariable Long id){
        return produtoService.remover(id);
    }
}
