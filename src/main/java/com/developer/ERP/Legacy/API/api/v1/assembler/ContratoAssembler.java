package com.developer.ERP.Legacy.API.api.v1.assembler;

import com.developer.ERP.Legacy.API.api.v1.controller.ClienteController;
import com.developer.ERP.Legacy.API.domain.model.Contratos;
import com.developer.ERP.Legacy.API.domain.representation.ContratoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ContratoAssembler extends RepresentationModelAssemblerSupport<Contratos,ContratoModel> {

    @Autowired
    private ModelMapper modelMapper;

    public ContratoAssembler() {
        super(ClienteController.class, ContratoModel.class);
    }
    @Override
    public ContratoModel toModel(Contratos contratos) {
        ContratoModel contratoModel = instantiateModel(contratos);

        contratoModel.add(linkTo(
                methodOn(ClienteController.class)
                        .listarContratosVinculados(contratoModel.getId(), contratoModel.getCliente().getId()))
                .withRel("[Final link to view the searched contract criteria]"));

        modelMapper.map(contratos,contratoModel);
        return contratoModel;
    }

}
