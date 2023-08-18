package com.developer.ERP.Legacy.API.api.controller;

import com.developer.ERP.Legacy.API.domain.model.Secao;
import com.developer.ERP.Legacy.API.domain.service.SecaoSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/secoes")
public class SecaoController {
    @Autowired
    private SecaoSevice secaoSevice;

    @GetMapping
    public List <Secao>secoes(){
        return secaoSevice.secoes();
    }
    @PostMapping
    public ResponseEntity <Secao>salvarSecao(@RequestBody Secao secao){
        return secaoSevice.salvarSecao(secao);
    }
    @PutMapping("/{id}")
    public Secao editarSecao(@PathVariable Long id, @RequestBody Secao secao){
        return secaoSevice.editarSecao(secao,id);
    }
    @GetMapping("/{secaoId}")
    public ResponseEntity <Secao>pesquisarPor(@PathVariable Long secaoId){
        return  secaoSevice.pesquisarPor(secaoId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> remover(@PathVariable Long id){
        return secaoSevice.remover(id);
    }
}
