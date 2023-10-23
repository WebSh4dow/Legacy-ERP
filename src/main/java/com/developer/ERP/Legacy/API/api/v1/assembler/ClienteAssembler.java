package com.developer.ERP.Legacy.API.api.v1.assembler;

import com.developer.ERP.Legacy.API.ApiLinks;
import com.developer.ERP.Legacy.API.api.v1.controller.ClienteController;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.model.Contratos;
import com.developer.ERP.Legacy.API.domain.representation.ClienteModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteAssembler
	extends RepresentationModelAssemblerSupport<Cliente,ClienteModel>{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApiLinks apiLinks;

    public ClienteAssembler() {
    	super(ClienteController.class,ClienteModel.class);
    }

    public ClienteModel toModel(Cliente cliente){

        ClienteModel clienteModel = instantiateModel(cliente);

        for (Contratos clientesContratos : cliente.getContratos()) {

            clienteModel.add(linkTo(
                    methodOn(ClienteController.class)
                            .pesquisar(null,clientesContratos.getId(),cliente.getId(),1))
                    .withRel("[Link to go references to contrato]"));

            clienteModel.add(linkTo(
                    methodOn(ClienteController.class)
                            .listarContratosVinculados(clientesContratos.getId(),cliente.getId()))
                    .withRel("[Link to go resource contratos]"));

            clienteModel.add(linkTo(
                    methodOn(ClienteController.class)
                            .getClienteById(cliente.getId()))
                    .withRel("[Link to go  cliente]"));


            modelMapper.map(cliente,clienteModel);
        }
            return clienteModel;
    }

    public CollectionModel<ClienteModel> toCollectionModel(Iterable<? extends Cliente> items) {
        List<ClienteModel> entityModelItems = StreamSupport.stream(items.spliterator(), false)
                .map(this::toModel).toList();

        return CollectionModel.of(entityModelItems,
                linkTo(methodOn(ClienteController.class).listarClientes()).withRel("[Link to list clientes]")
        );
    }


}
