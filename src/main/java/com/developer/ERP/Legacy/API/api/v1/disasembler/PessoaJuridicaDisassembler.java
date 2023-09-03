package com.developer.ERP.Legacy.API.api.v1.disasembler;

import com.developer.ERP.Legacy.API.api.v1.request.ClienteRequest;
import com.developer.ERP.Legacy.API.api.v1.request.PessoaJuridicaRequest;
import com.developer.ERP.Legacy.API.domain.model.PessoaJuridica;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaJuridicaDisassembler {
    @Autowired
    private ModelMapper modelMapper;
    public PessoaJuridica toDomainModelObject(ClienteRequest pessoaJuridicaRequest){
        return modelMapper.map(pessoaJuridicaRequest,PessoaJuridica.class);
    }
    public void copyToDomainObject(PessoaJuridicaRequest pessoaJuridicaRequest, PessoaJuridica pessoaJuridica){
        modelMapper.map(pessoaJuridicaRequest,pessoaJuridica);
    }
}
