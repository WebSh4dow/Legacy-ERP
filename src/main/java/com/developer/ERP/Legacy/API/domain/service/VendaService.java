package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.model.Venda;
import com.developer.ERP.Legacy.API.domain.repository.VendaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;
    
    @Transactional
    public ResponseEntity <Venda> incluir (Venda venda){
        Venda incluir = vendaRepository.save(venda);
        return ResponseEntity.ok().body(incluir);
    }
    
    @Transactional
    public Venda editar(Long id,Venda venda){
        Venda vendas = vendaRepository.findById(id).get();
        if (vendas == null){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(venda,vendas,"id");
        return vendaRepository.save(vendas);
    }
    
    public List <Venda>vendas(){
        return vendaRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }
    
    public ResponseEntity<Venda> buscarVendaLancada(Long id){
        Venda lancamento = vendaRepository.findById(id)
                .orElse(null);
        return ResponseEntity.ok().body(lancamento);
    }

}
