package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper;

import com.developer.ERP.Legacy.API.domain.model.ContaBancaria;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ContaBancariaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ContaBancariaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ContaBancaria toContaBancaria(ContaBancariaEntity contaBancariaEntity) {
        return modelMapper.map(contaBancariaEntity, ContaBancaria.class);
    }

    public ContaBancariaEntity toEntity(ContaBancaria contaBancaria) {
        return modelMapper.map(contaBancaria,ContaBancariaEntity.class);
    }
}
