package com.developer.ERP.Legacy.API.api.Controller;

import com.developer.ERP.Legacy.API.domain.Model.Vendedor;
import com.developer.ERP.Legacy.API.domain.Service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("vendedores")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;
    @GetMapping
    public List <Vendedor>listarTodos(){
        return vendedorService.listarTodos();
    }
    @GetMapping("/{id}")
    public Object buscarPodId(@PathVariable Long id){
        return vendedorService.listarPorId(id);
    }
    @PostMapping
    public ResponseEntity <Vendedor>salvarRegistro(@RequestBody Vendedor vendedor){
        return vendedorService.registrarVendedor(vendedor);
    }
    @PutMapping("/{id}")
    public void editarRegistro(@PathVariable Long id, @RequestBody Vendedor vendedor){
        vendedorService.alterarVendedor(id, vendedor);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>>removerRegistro(@PathVariable Long id){
        return vendedorService.remover(id);
    }

}
