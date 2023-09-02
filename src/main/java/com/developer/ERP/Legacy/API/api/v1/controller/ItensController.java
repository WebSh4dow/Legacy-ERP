package com.developer.ERP.Legacy.API.api.v1.controller;

import com.developer.ERP.Legacy.API.domain.model.Itens;
import com.developer.ERP.Legacy.API.domain.service.ItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("api/itens")
public class ItensController {
    @Autowired
    private ItensService itensService;
    @GetMapping
    public List <Itens>listarTodos(){
        return itensService.todos();
    }
    @GetMapping("/{id}")
    public ResponseEntity <Itens>listarPor(@PathVariable Long id){
        return itensService.pesquisarPor(id);
    }
    @PostMapping
    public ResponseEntity <Itens> salvar(@RequestBody Itens itens){
        return itensService.salvar(itens);
    }
    @PutMapping("/{id}")
    public Itens editar(@PathVariable Long id,@RequestBody Itens itens){
        return itensService.editarItens(id, itens);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> remover(@PathVariable Long id){
        return itensService.remover(id);
    }
}
