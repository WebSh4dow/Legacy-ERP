package com.developer.ERP.Legacy.API.infrastructure.adapters.config;

import com.developer.ERP.Legacy.API.domain.service.ContaCorrenteService;
import com.developer.ERP.Legacy.API.domain.service.ImovelService;
import com.developer.ERP.Legacy.API.domain.service.ProprietarioService;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.persistentAdapters.ContaCorrentePersistenceAdapter;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.persistentAdapters.ImovelPersistenceAdapter;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.persistentAdapters.ProprietarioPersistenceAdapter;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper.*;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository.ContaCorrenteRepository;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository.ImovelRepository;
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
    public EnderecoMapper enderecoMapper () {
        return new EnderecoMapper();
    }

    @Bean
    public DadosPessoaisMapper dadosPessoaisMapper () {
        return new DadosPessoaisMapper();
    }

    @Bean
    public ImovelMapper imovelMapper() {
        return new ImovelMapper();
    }

    @Bean
    public ConjugueMapper conjugueMapper() {
        return new ConjugueMapper();
    }

    @Bean
    public EmpreendimentoMapper empreendimentoMapper() {
        return new EmpreendimentoMapper();
    }

    @Bean
    public ProprietarioPersistenceAdapter proprietarioPersistenceAdapter(ProprietarioRepository proprietarioRepository, ProprietarioMapper proprietarioMapper) {
        return new ProprietarioPersistenceAdapter(proprietarioRepository, proprietarioMapper);
    }

    @Bean
    public ImovelPersistenceAdapter imovelPersistenceAdapter(ImovelRepository imovelRepository, ImovelMapper imovelMapper) {
        return new ImovelPersistenceAdapter(imovelRepository, imovelMapper);
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
    public ImovelService imovelService(ImovelPersistenceAdapter imovelPersistenceAdapter) {
        return new ImovelService(imovelPersistenceAdapter);
    }

    @Bean
    public ContaCorrenteService contaCorrenteService(ContaCorrentePersistenceAdapter contaCorrentePersistenceAdapter) {
        return new ContaCorrenteService(contaCorrentePersistenceAdapter);
    }
}