package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper;

import com.developer.ERP.Legacy.API.domain.model.Empreendimento;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.EmpreendimentoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class EmpreendimentoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Empreendimento toEmpreendimento(EmpreendimentoEntity empreendimentoEntity) {
        return modelMapper.map(empreendimentoEntity,Empreendimento.class);
    }

    public EmpreendimentoEntity toEntity(Empreendimento empreendimento) {
        return modelMapper.map(empreendimento, EmpreendimentoEntity.class);
    }
}
