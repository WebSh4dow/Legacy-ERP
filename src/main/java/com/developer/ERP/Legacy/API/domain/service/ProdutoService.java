package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.model.Produto;
import com.developer.ERP.Legacy.API.domain.repository.ProdutoRepository;
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
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ResponseEntity<Produto> salvar(Produto produto){
        Produto objetoProdutos = produtoRepository.save(produto);
        return ResponseEntity.ok().body(objetoProdutos);
    }
    public List <Produto> todos(){
        return produtoRepository.findAll();
    }
    public ResponseEntity <Produto> pesquisarPor(Long id){
        Produto pesquisar = produtoRepository.findById(id)
                .orElse(null);
        return ResponseEntity.ok().body(pesquisar);
    }
    @Transactional
    public Produto editarProdutos(Long id,Produto produtos){
        Produto pesquisarId = produtoRepository.findById(id)
                .orElse(null);
        if (pesquisarId ==null){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(produtos,pesquisarId,"id");
        return produtoRepository.save(pesquisarId);
    }
    @Transactional
    public ResponseEntity<Map<String, Boolean>>remover(Long id){
        Produto pesquisarItens = produtoRepository.findById(id)
                .orElse(null);
        produtoRepository.delete(pesquisarItens);
        Map <String,Boolean> response = new HashMap<>();
        response.put("Removido",true);
        return ResponseEntity.ok().body(response);
    }

}
