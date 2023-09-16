package com.developer.ERP.Legacy.API.api.v1.assembler;


import com.developer.ERP.Legacy.API.api.v1.controller.ClienteController;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.representation.ClienteModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteAssembler 
	extends RepresentationModelAssemblerSupport<Cliente,ClienteModel>{

    @Autowired
    private ModelMapper modelMapper;
     
    public ClienteAssembler() {
    	super(ClienteController.class,ClienteModel.class);
    }
    
    public ClienteModel toModel(Cliente cliente){
    	ClienteModel clienteModel = createModelWithId(cliente.getId(), cliente);
    	modelMapper.map(cliente,clienteModel);
        return clienteModel;
    }
    public List<ClienteModel> toCollectionModel(List<Cliente> clientes){
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
