package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper;

import com.developer.ERP.Legacy.API.domain.model.Proprietario;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ProprietarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProprietarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Proprietario toProprietario(ProprietarioEntity proprietarioEntity) {
        return modelMapper.map(proprietarioEntity, Proprietario.class);
    }

    public ProprietarioEntity toEntity(Proprietario proprietario) {
        return modelMapper.map(proprietario, ProprietarioEntity.class);
    }
}
