package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.model.Fornecedor;
import com.developer.ERP.Legacy.API.domain.repository.FornecedorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }
    public List <Fornecedor>listarFornecedor(){
        return new ArrayList<>(fornecedorRepository.findAll());
    }
    public ResponseEntity<Fornecedor> salvarFornecedor(Fornecedor fornecedor){
        Fornecedor salvarFornecedor = fornecedorRepository.save(fornecedor);
        return ResponseEntity.ok().body(salvarFornecedor);
    }
    public ResponseEntity<Fornecedor>porId(Long id){
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElse(null);
        return ResponseEntity.ok().body(fornecedor);
    }
    @Transactional
    public Fornecedor editar(Fornecedor fornecedor, Long id){
        Fornecedor editarOuSalvar = fornecedorRepository.findById(id).get();
        if (editarOuSalvar == null){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(fornecedor,editarOuSalvar,"id");
        return fornecedorRepository.save(editarOuSalvar);
    }
    @Transactional
    public ResponseEntity<Map<String, Boolean>> remover(Long id){
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElse(null);
        fornecedorRepository.delete(fornecedor);

        Map<String,Boolean>response = new HashMap<>();
        response.put("removido",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



}
