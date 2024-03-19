package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper;

import com.developer.ERP.Legacy.API.domain.model.ContaCorrente;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ContaCorrenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ContaCorrenteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ContaCorrente toContaBancaria(ContaCorrenteEntity contaCorrenteEntity) {
        return modelMapper.map(contaCorrenteEntity, ContaCorrente.class);
    }

    public ContaCorrenteEntity toEntity(ContaCorrente contaCorrente) {
        return modelMapper.map(contaCorrente, ContaCorrenteEntity.class);
    }
}
