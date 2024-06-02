package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper;

import com.developer.ERP.Legacy.API.domain.model.Conjugue;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ConjugueEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ConjugueMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Conjugue toConjugue(ConjugueEntity conjugueEntity) {
        return modelMapper.map(conjugueEntity,Conjugue.class);
    }

    public ConjugueEntity toEntity(Conjugue conjugue) {
        return modelMapper.map(conjugue, ConjugueEntity.class);
    }

}
