package com.developer.ERP.Legacy.API.api.v1.assembler;

import com.developer.ERP.Legacy.API.api.v1.request.ClienteRequest;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.representation.ClienteRepresentationModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteAssembler {

    @Autowired
    private ModelMapper modelMapper;
    public ClienteRepresentationModel toModel(Cliente cliente){
        return modelMapper.map(cliente,ClienteRepresentationModel.class);
    }
    public List<ClienteRepresentationModel> toCollectionModel(List<Cliente> clientes){
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }


}
