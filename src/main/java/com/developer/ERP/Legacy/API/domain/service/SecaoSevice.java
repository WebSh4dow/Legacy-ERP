package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.model.Secao;
import com.developer.ERP.Legacy.API.domain.repository.SecaoRepository;
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
public class SecaoSevice {
    @Autowired
    private SecaoRepository secaoRepository;
    public List <Secao>secoes(){
        return secaoRepository.findAll();
    }
    @Transactional
    public ResponseEntity <Secao>pesquisarPor(Long secaoId){
        Secao  pesqusiarPorSecoesExistentes = secaoRepository.findById(secaoId)
                .orElse(null);
        return ResponseEntity.ok().body(pesqusiarPorSecoesExistentes);
    }
    @Transactional
    public ResponseEntity <Secao>salvarSecao(Secao secao){
        Secao salvarSecaoAtual = secaoRepository.save(secao);
        return  ResponseEntity.ok().body(salvarSecaoAtual);
    }
    @Transactional
    public Secao editarSecao(Secao secao,Long id){
        Secao pesquisarSecaoAtual = secaoRepository.findById(id)
                .orElse(null);
        if (pesquisarSecaoAtual == null){
            throw new  EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(secao,pesquisarSecaoAtual,"id");
        return secaoRepository.save(pesquisarSecaoAtual);
    }
    @Transactional
    public ResponseEntity<Map<String, Boolean>>remover(Long id){
        Secao buscarNovaSecao = secaoRepository.findById(id)
                .orElse(null);
        secaoRepository.delete(buscarNovaSecao);
        Map <String,Boolean> response = new HashMap<>();
        response.put("Removido",true);
        return ResponseEntity.ok().body(response);
    }
}
