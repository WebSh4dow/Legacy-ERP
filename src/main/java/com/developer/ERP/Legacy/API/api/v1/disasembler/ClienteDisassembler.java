package com.developer.ERP.Legacy.API.api.v1.disasembler;

import com.developer.ERP.Legacy.API.api.v1.request.ClienteRequest;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Cliente toDomainObject(ClienteRequest clienteRequest){
        return modelMapper.map(clienteRequest, Cliente.class);
    }
    public void copyToDomainObject(ClienteRequest clienteRequest, Cliente cliente){
        modelMapper.map(clienteRequest,cliente);
    }
}
