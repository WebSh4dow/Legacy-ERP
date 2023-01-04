package com.developer.ERP.Legacy.API.Service;

import com.developer.ERP.Legacy.API.Model.Endereco;
import com.developer.ERP.Legacy.API.Repository.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    public List <Endereco>listarTodos() {
        return enderecoRepository.findAll();
    }
    public ResponseEntity <Endereco>listarPor(Long id){
        Endereco endereco = enderecoRepository.findById(id)
                .orElse(null);
        return ResponseEntity.ok().body(endereco);
    }
    public Endereco editar(Endereco endereco,Long id){
        Endereco editarOuSalvar = enderecoRepository.findById(id).get();
        if (editarOuSalvar == null){
             throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(endereco,editarOuSalvar,"id");
        return enderecoRepository.save(editarOuSalvar);
    }
    @Transactional
    public ResponseEntity <Endereco> salvar(Endereco endereco){
        Endereco cadastrar = enderecoRepository.save(endereco);
        return ResponseEntity.ok().body(cadastrar);
    }
    @Transactional
    public ResponseEntity<Map<String, Boolean>> remover(Long id){
        Endereco endereco = enderecoRepository.findById(id)
                .orElse(null);
        enderecoRepository.delete(endereco);

        Map<String,Boolean>response = new HashMap<>();
        response.put("removido",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
