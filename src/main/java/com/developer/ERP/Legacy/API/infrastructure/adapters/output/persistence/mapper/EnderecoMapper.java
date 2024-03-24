package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper;

import com.developer.ERP.Legacy.API.domain.model.Endereco;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.EnderecoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class EnderecoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Endereco toEndereco(EnderecoEntity enderecoEntity) {
        return modelMapper.map(enderecoEntity,Endereco.class);
    }

    public EnderecoEntity toEntity(Endereco endereco) {
        return modelMapper.map(endereco, EnderecoEntity.class);
    }
}
