package com.developer.ERP.Legacy.API.Controller;

import com.developer.ERP.Legacy.API.Model.Endereco;
import com.developer.ERP.Legacy.API.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List <Endereco>listarTodos(){
        return enderecoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Endereco>listarPor(@PathVariable Long id){
        return enderecoService.listarPor(id);
    }
    @PostMapping
    public ResponseEntity <Endereco> salvar(@RequestBody Endereco endereco){
        return enderecoService.salvar(endereco);
    }
    @PutMapping("/{id}")
    public Endereco editar(@PathVariable Long id,@RequestBody Endereco endereco){
        return enderecoService.editar(endereco, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> remover(@PathVariable Long id){
        return enderecoService.remover(id);
    }


}
