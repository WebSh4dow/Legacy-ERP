package com.developer.ERP.Legacy.API.domain.Service;

import com.developer.ERP.Legacy.API.domain.Model.Transportadora;
import com.developer.ERP.Legacy.API.domain.Repository.TransportadoraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class TransportadoraService {
    @Autowired
    private TransportadoraRepository transportadoraRepository;
    public List <Transportadora> transportadoras(){
        return transportadoraRepository.findAll();
    }
    public ResponseEntity<Transportadora> adcionarTransportadora (Transportadora transportadora){
        Transportadora transportadoras = transportadoraRepository.save(transportadora);
        return ResponseEntity.ok().body(transportadora);
    }
    public Transportadora alterar(Transportadora transportadora, Long id){
        Transportadora procurarTransportadora = transportadoraRepository.findById(id)
                .orElse(null);

        if (procurarTransportadora == null){
            throw new EmptyResultDataAccessException(1);
        }
        Transportadora editar = transportadoraRepository.save(procurarTransportadora);
        BeanUtils.copyProperties(transportadora,editar,"id");
        return editar;
    }
    public ResponseEntity <Transportadora> pesquisarPoridentificador(Long id){
        Transportadora transportadora = transportadoraRepository.findById(id)
                .orElse(null);
        return ResponseEntity.ok().body(transportadora);
    }
    public ResponseEntity<Map<String, Boolean>> remover(Long id){
        Transportadora transportadora = transportadoraRepository.findById(id)
                .orElse(null);
        transportadoraRepository.delete(transportadora);

        Map <String,Boolean>response = new HashMap<>();
        response.put("Transportadora foi removida com sucesso!!!!",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
