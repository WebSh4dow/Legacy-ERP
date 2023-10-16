package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.exceptions.runtime.BussinesException;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.HandlerNotFoundException;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.ClienteRepository;
import com.developer.ERP.Legacy.API.domain.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscar(Long contratoId,Long clienteId){
       List<Cliente> buscarContratos = clienteRepository.findContratosVinculateCliente(contratoId,clienteId)
               .stream().collect(Collectors.toList());

        if (buscarContratos.size() == 0){
            throw new BussinesException("Usando o criterio de pesquisa atual não foi possivel encontrar o contrato de id " + contratoId + "cliente atual de id"  + clienteId + " pesquisado não possui contratos vinculados");
        }

        return buscarContratos;
    }
}
