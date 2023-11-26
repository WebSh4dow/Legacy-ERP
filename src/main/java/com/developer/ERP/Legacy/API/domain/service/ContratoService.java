package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.exceptions.runtime.BussinesException;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.ClienteRepository;
import com.developer.ERP.Legacy.API.domain.repository.ContratoRepository;
import com.developer.ERP.Legacy.API.domain.representation.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarContrato(Long clienteId,Long contratoId){
       List<Cliente> buscarContratos = clienteRepository.findContratosVinculateCliente(contratoId,clienteId);

       if (buscarContratos.size() == 0){
            throw new BussinesException("Usando o criterio de pesquisa atual não foi possivel encontrar o contrato vinculado ao cliente atual da pesquisa");
       }
       return buscarContratos;
    }

}
