package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence;

import com.developer.ERP.Legacy.API.application.ports.output.ProprietarioOutputPort;
import com.developer.ERP.Legacy.API.domain.exception.CpfCadastradoException;
import com.developer.ERP.Legacy.API.domain.model.Proprietario;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ProprietarioEntity;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper.ProprietarioMapper;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProprietarioPersistenceAdapter implements ProprietarioOutputPort {

    private final ProprietarioRepository repository;

    private final ProprietarioMapper proprietarioMapper;

    private final static String MSG_CPF_CADASTRADO = "Existe um cpf ou rg já cadastrados em nosso sistema, tente outro diferente.";

    @Override
    public Proprietario saveProprietario(Proprietario proprietario) {
        ProprietarioEntity proprietarioEntity = proprietarioMapper.toEntity(proprietario);

        if (repository.existsProprietarioByCpfAndRgIgnoreCase(proprietario.getCpf(), proprietario.getRg())) {
            throw new CpfCadastradoException(MSG_CPF_CADASTRADO);
        }

        repository.save(proprietarioEntity);
        return proprietarioMapper.toProprietario(proprietarioEntity);
    }

    @Override
    public Optional<Proprietario> getProprietarioByCodigo(Long codigo) {
        Optional<ProprietarioEntity> proprietarioEntity = repository.findById(codigo);

        if (proprietarioEntity.isEmpty()) {
            return Optional.empty();
        }
        Proprietario proprietario = proprietarioMapper.toProprietario(proprietarioEntity.get());
        return Optional.of(proprietario);
    }

    @Override
    public List<Proprietario> getProprietarios() {
        return repository.findAll().
                stream().map(proprietarioMapper :: toProprietario)
                .collect(Collectors.toList());
    }
}
