package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.model.Funcionario;
import com.developer.ERP.Legacy.API.domain.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    public List<Funcionario> listarTodos(){
        return funcionarioRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }
    public ResponseEntity <Funcionario>listarPorId(Long id){
       Funcionario pesquisarFuncionario = funcionarioRepository.findById(id)
               .orElse(null);
       return ResponseEntity.ok().body(pesquisarFuncionario);
    }
    @Transactional
    public ResponseEntity <Funcionario>incluir(Funcionario funcionario){
        Funcionario incluirFuncionario = funcionarioRepository.save(funcionario);
        return ResponseEntity.ok().body(incluirFuncionario);
    }
    @Transactional
    public Funcionario editar(Funcionario funcionario, Long id){
        Funcionario editarOuSalvar = funcionarioRepository.findById(id).get();
        if (editarOuSalvar == null){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(funcionario,editarOuSalvar,"id");
        return funcionarioRepository.save(editarOuSalvar);
    }
    @Transactional
    public ResponseEntity<Map<String, Boolean>> remover(Long id){
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElse(null);
        funcionarioRepository.delete(funcionario);

        Map<String,Boolean>response = new HashMap<>();
        response.put("removido",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
