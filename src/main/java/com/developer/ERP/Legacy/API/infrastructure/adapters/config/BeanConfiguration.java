package com.developer.ERP.Legacy.API.infrastructure.adapters.config;

import com.developer.ERP.Legacy.API.domain.service.ContaCorrenteService;
import com.developer.ERP.Legacy.API.domain.service.ProprietarioService;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.ContaCorrentePersistenceAdapter;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.ProprietarioPersistenceAdapter;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper.BancoMapper;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper.ContaCorrenteMapper;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper.ProprietarioMapper;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository.ContaCorrenteRepository;
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
    public ContaCorrenteMapper contaBancariaMapper() {
        return new ContaCorrenteMapper();
    }

    @Bean
    public BancoMapper bancoMapper() {
        return new BancoMapper();
    }

    @Bean
    public ProprietarioPersistenceAdapter proprietarioPersistenceAdapter(ProprietarioRepository proprietarioRepository, ProprietarioMapper proprietarioMapper) {
        return new ProprietarioPersistenceAdapter(proprietarioRepository, proprietarioMapper);
    }

    @Bean
    public ContaCorrentePersistenceAdapter contaCorrentePersistenceAdapter(ContaCorrenteRepository contaCorrenteRepository, ContaCorrenteMapper contaCorrenteMapper) {
        return new ContaCorrentePersistenceAdapter(contaCorrenteRepository,contaCorrenteMapper);
    }

    @Bean
    public ProprietarioService proprietarioService(ProprietarioPersistenceAdapter proprietarioPersistenceAdapter) {
        return new ProprietarioService(proprietarioPersistenceAdapter);
    }

    @Bean
    public ContaCorrenteService contaCorrenteService(ContaCorrentePersistenceAdapter contaCorrentePersistenceAdapter) {
        return new ContaCorrenteService(contaCorrentePersistenceAdapter);
    }

}
