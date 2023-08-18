package com.developer.ERP.Legacy.API.api.controller;

import com.developer.ERP.Legacy.API.domain.model.Setor;
import com.developer.ERP.Legacy.API.domain.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/setores")
public class SetorController {
    @Autowired
    private SetorService setorService;
    @PostMapping
    public ResponseEntity<Setor> adcionar(@RequestBody Setor setor){
        return setorService.adcionar(setor);
    }
    @GetMapping
    public List <Setor>setores(){
        return setorService.setores();
    }
    @GetMapping("/{setorId}")
    public ResponseEntity<Setor> buscarPor(@PathVariable Long setorId){
        return setorService.buscar(setorId);
    }
    @PutMapping("/{id}")
    public Setor alterar(@RequestBody Setor setor,@PathVariable Long id){
        return setorService.alterar(id,setor);
    }
    @PostMapping("vincular/{setorId}/{departamentoId}")
    public boolean vincular(@PathVariable Long setorId,@PathVariable Long departamentoId){
        return setorService.vincularDepartamentos(setorId,departamentoId);
    }
    @DeleteMapping("desvincular/{setorId}/{departamentoId}")
    public boolean desvincular(@PathVariable Long setorId,@PathVariable Long departamentoId){
        return setorService.desvincularDepartamentos(setorId,departamentoId);
    }
    @DeleteMapping("/{setorId}")
    public ResponseEntity<Map<String, Boolean>> remover(@PathVariable Long setorId){
        return setorService.remover(setorId);
    }
}
