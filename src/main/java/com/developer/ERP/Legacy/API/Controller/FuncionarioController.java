package com.developer.ERP.Legacy.API.Controller;

import com.developer.ERP.Legacy.API.Model.Funcionario;
import com.developer.ERP.Legacy.API.Repository.FuncionarioRepository;
import com.developer.ERP.Legacy.API.Service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @GetMapping
    public List <Funcionario> listarTodos(){
        return funcionarioService.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity <Funcionario>listarPorId(@PathVariable Long id){
        return funcionarioService.listarPorId(id);
    }

    @GetMapping("/login-inativo")
    public List <Funcionario> findByInativos(@RequestParam(("inativo"))int inativo){
        return funcionarioRepository.findByInativoContaining(inativo);
    }
    @GetMapping("/login-ativo")
    public List <Funcionario> findByAtivos(@RequestParam(("ativo"))int ativo){
        return funcionarioRepository.findByAtivoContaining(ativo);
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
