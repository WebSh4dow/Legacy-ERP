package com.developer.ERP.Legacy.API.infrastructure.adapters.config;

import com.developer.ERP.Legacy.API.domain.service.ProprietarioService;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.ProprietarioPersistenceAdapter;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper.ContaBancariaMapper;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper.ProprietarioMapper;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository.ProprietarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ProprietarioMapper proprietarioMapper() {
        return new ProprietarioMapper();
    }

    @Bean
    public ContaBancariaMapper contaBancariaMapper() {
        return new ContaBancariaMapper();
    }

    @Bean
    public ProprietarioPersistenceAdapter proprietarioPersistenceAdapter(ProprietarioRepository proprietarioRepository, ProprietarioMapper proprietarioMapper) {
        return new ProprietarioPersistenceAdapter(proprietarioRepository, proprietarioMapper);
    }

    @Bean
    public ProprietarioService proprietarioService(ProprietarioPersistenceAdapter proprietarioPersistenceAdapter) {
        return new ProprietarioService(proprietarioPersistenceAdapter);
    }

}
