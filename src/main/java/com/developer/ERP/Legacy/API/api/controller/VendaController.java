package com.developer.ERP.Legacy.API.api.controller;

import com.developer.ERP.Legacy.API.domain.model.Venda;
import com.developer.ERP.Legacy.API.domain.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vendas")
public class VendaController {
    @Autowired
    private VendaService vendaService;
    @GetMapping
    public List <Venda>vendas(){
        return vendaService.vendas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVendaLancada(@PathVariable Long id){
        return vendaService.buscarVendaLancada(id);
    }
    @PostMapping
    ResponseEntity <Venda> incluirVenda(@RequestBody Venda venda){
        return vendaService.incluir(venda);
    }



}
