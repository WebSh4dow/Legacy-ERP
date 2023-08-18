package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.model.Itens;
import com.developer.ERP.Legacy.API.domain.repository.ItensRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ItensService {
    @Autowired
    private ItensRepository itensRepository;

    @Transactional
    public ResponseEntity<Itens> salvar(Itens itens){
        Itens objetoItens = itensRepository.save(itens);
        return ResponseEntity.ok().body(objetoItens);
    }
    public List <Itens> todos(){
        return itensRepository.findAll();
    }
    public ResponseEntity <Itens> pesquisarPor(Long id){
        Itens pesquisar = itensRepository.findById(id)
                .orElse(null);
        return ResponseEntity.ok().body(pesquisar);
    }
    @Transactional
    public Itens editarItens(Long id,Itens itens){
        Itens pesquisarId = itensRepository.findById(id)
                .orElse(null);
        if (pesquisarId ==null){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(itens,pesquisarId,"id");
        return itensRepository.save(pesquisarId);
    }

    @Transactional
    public ResponseEntity<Map<String, Boolean>>remover(Long id){
        Itens pesquisarItens = itensRepository.findById(id)
                .orElse(null);
        itensRepository.delete(pesquisarItens);
        Map <String,Boolean> response = new HashMap<>();
        response.put("Removido",true);
        return ResponseEntity.ok().body(response);
    }

}
