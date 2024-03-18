package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence;

import com.developer.ERP.Legacy.API.application.ports.output.ContaCorrenteOutputPort;
import com.developer.ERP.Legacy.API.domain.exception.ContaCorrenteNotFoundException;
import com.developer.ERP.Legacy.API.domain.model.ContaCorrente;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ContaCorrenteEntity;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper.ContaCorrenteMapper;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository.ContaCorrenteRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ContaCorrentePersistenceAdapter implements ContaCorrenteOutputPort {

    private final ContaCorrenteRepository repository;
    private final ContaCorrenteMapper contaCorrenteMapper;

    @Override
    public ContaCorrente saveContaCorrente(ContaCorrente contaCorrente) {
        ContaCorrenteEntity contaCorrenteEntity = contaCorrenteMapper.toEntity(contaCorrente);

        repository.save(contaCorrenteEntity);
        return contaCorrenteMapper.toContaBancaria(contaCorrenteEntity);
    }

    @Override
    public Optional<ContaCorrente> getContaCorrenteByCodigo(Long codigo) {

        Optional<ContaCorrenteEntity> optionalContaCorrente = repository.findById(codigo);

        if (optionalContaCorrente.isEmpty()) {
            throw new ContaCorrenteNotFoundException("Não existe uma conta corrente com o código: " + codigo);
        }
        ContaCorrente contaCorrente = contaCorrenteMapper.toContaBancaria(optionalContaCorrente.get());

        return Optional.of(contaCorrente);
    }

    @Override
    public List<ContaCorrente> getContasCorrentes() {
        return repository.findAll().stream().map(contaCorrenteMapper :: toContaBancaria)
                .collect(Collectors.toList());
    }
}
