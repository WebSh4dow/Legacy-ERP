package com.developer.ERP.Legacy.API.api.v1.controller;

import com.developer.ERP.Legacy.API.domain.model.Funcionario;
import com.developer.ERP.Legacy.API.domain.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("api/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    
    @GetMapping
    public List <Funcionario> listarTodos(){
        return funcionarioService.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity <Funcionario>listarPorId(@PathVariable Long id){
        return funcionarioService.listarPorId(id);
    }

    @PostMapping
    public ResponseEntity <Funcionario>salvarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.incluir(funcionario);
    }
    @PutMapping("/{id}")
    public Funcionario alterarFuncionario(@RequestBody Funcionario funcionario,@PathVariable Long id){
        return funcionarioService.editar(funcionario, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> remover(@PathVariable Long id){
       return funcionarioService.remover(id);
    }
}
