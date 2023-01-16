package com.developer.ERP.Legacy.API.domain.Service;

import com.developer.ERP.Legacy.API.domain.Exceptions.HandlerNotFoundException;
import com.developer.ERP.Legacy.API.domain.Model.Venda;
import com.developer.ERP.Legacy.API.domain.Model.Vendedor;
import com.developer.ERP.Legacy.API.domain.Repository.VendaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.developer.ERP.Legacy.API.infrastructure.service.Messages.VendedorMessage.MSG_INFORMATION_NOT_FOUND_VENDEDOR;

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
