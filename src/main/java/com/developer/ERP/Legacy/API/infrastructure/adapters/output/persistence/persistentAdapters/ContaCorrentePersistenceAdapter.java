package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.persistentAdapters;

import com.developer.ERP.Legacy.API.application.ports.output.ContaCorrenteOutputPort;
import com.developer.ERP.Legacy.API.domain.exception.ContaCorrenteNotFoundException;
import com.developer.ERP.Legacy.API.domain.exception.ExisteUmaContaCadastradaException;
import com.developer.ERP.Legacy.API.domain.model.ContaCorrente;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ContaCorrenteEntity;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper.ContaCorrenteMapper;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository.ContaCorrenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ContaCorrentePersistenceAdapter implements ContaCorrenteOutputPort {

    private final ContaCorrenteRepository repository;


    private final ContaCorrenteMapper contaCorrenteMapper;

    private final static String MSG_AGENCIA_E_CONTA_CORRENTES_EXISTENTES = "Verifique se o número da agência e o número da conta já foram cadastrados em um cadastro anterior.";

    private final static String MSG_CODIGO_CONTA_CORRENTE = "Não existe uma conta corrente com o código: ";

    private final static String MSG_CODIGO_CONTA_CORRENTE_NAO_EXISTE = "Conta Corrente inexistente";

    @Override
    public ContaCorrente saveContaCorrente(ContaCorrente contaCorrente) {
        ContaCorrenteEntity contaCorrenteEntity = contaCorrenteMapper.toEntity(contaCorrente);

        if (repository.existsContaCorrenteByAgenciaAndNumeroContaCorrenteIgnoreCase
                (contaCorrenteEntity.getAgencia(), contaCorrenteEntity.getNumeroContaCorrente())) {
            throw new ExisteUmaContaCadastradaException(MSG_AGENCIA_E_CONTA_CORRENTES_EXISTENTES);
        }

        repository.save(contaCorrenteEntity);
        return contaCorrenteMapper.toContaBancaria(contaCorrenteEntity);
    }

    @Override
    public Optional<ContaCorrente> getContaCorrenteByCodigo(Long codigo) {

        Optional<ContaCorrenteEntity> optionalContaCorrente = repository.findById(codigo);

        if (optionalContaCorrente.isEmpty()) {
                throw new ContaCorrenteNotFoundException(MSG_CODIGO_CONTA_CORRENTE + codigo);
        }
        ContaCorrente contaCorrente = contaCorrenteMapper.toContaBancaria(optionalContaCorrente.get());

        return Optional.of(contaCorrente);
    }

    @Override
    public List<ContaCorrente> getContasCorrentes() {
        return repository.findAll().stream().map(contaCorrenteMapper::toContaBancaria)
                .collect(Collectors.toList());
    }

    @Override
    public ContaCorrente updateContaBancaria(ContaCorrente contaCorrente) {

            if (contaCorrente.getCodigo() == null) {
                throw new ContaCorrenteNotFoundException(MSG_CODIGO_CONTA_CORRENTE_NAO_EXISTE);
            }

            Optional<ContaCorrenteEntity> optionalContaCorrente = repository.findById(contaCorrente.getCodigo());

            if (optionalContaCorrente.isEmpty()) {
                throw new ContaCorrenteNotFoundException(MSG_CODIGO_CONTA_CORRENTE + contaCorrente.getCodigo());
            }

            ContaCorrenteEntity contaCorrenteEntity = optionalContaCorrente.get();
            BeanUtils.copyProperties(contaCorrente, contaCorrenteEntity);

            repository.save(contaCorrenteEntity);

            return contaCorrenteMapper.toContaBancaria(contaCorrenteEntity);
    }
}