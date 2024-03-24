package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper;

import com.developer.ERP.Legacy.API.domain.model.DadosPessoais;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.DadosPessoaisEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DadosPessoaisMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DadosPessoais toDadosPessoaisEntity(DadosPessoaisEntity dadosPessoaisEntity) {
        return modelMapper.map(dadosPessoaisEntity, DadosPessoais.class);
    }

    public DadosPessoaisEntity toEntity(DadosPessoais dadosPessoais) {
        return modelMapper.map(dadosPessoais, DadosPessoaisEntity.class);
    }
}
