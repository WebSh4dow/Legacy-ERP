package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.persistentAdapters;

import com.developer.ERP.Legacy.API.application.ports.output.ImovelOutputPort;
import com.developer.ERP.Legacy.API.domain.model.Imovel;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper.ImovelMapper;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository.ImovelRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ImovelPersistenceAdapter implements ImovelOutputPort {

    private final ImovelRepository repository;
    private final ImovelMapper mapper;

    @Override
    public List<Imovel> historicoImoveis() {
        return repository.historicoImoveis().stream().map(mapper::toImovel)
                .collect(Collectors.toList());
    }
}
