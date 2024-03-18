package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper;

import com.developer.ERP.Legacy.API.domain.model.Banco;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.BancoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BancoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Banco toBanco(BancoEntity bancoEntity) {
        return modelMapper.map(bancoEntity, Banco.class);
    }

    public BancoEntity bancoEntity(Banco banco) {
        return modelMapper.map(banco, BancoEntity.class);
    }
}
