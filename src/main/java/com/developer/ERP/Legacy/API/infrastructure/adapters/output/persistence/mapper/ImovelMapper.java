package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper;

import com.developer.ERP.Legacy.API.domain.model.Imovel;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ImovelEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ImovelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Imovel toImovel(ImovelEntity imovelEntity) {
        return modelMapper.map(imovelEntity,Imovel.class);
    }

    public ImovelEntity toEntity(Imovel imovel) {
        return modelMapper.map(imovel, ImovelEntity.class);
    }

}
