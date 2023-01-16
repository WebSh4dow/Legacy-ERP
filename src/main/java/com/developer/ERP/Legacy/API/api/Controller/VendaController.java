package com.developer.ERP.Legacy.API.api.Controller;

import com.developer.ERP.Legacy.API.domain.Model.Venda;
import com.developer.ERP.Legacy.API.domain.Service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
