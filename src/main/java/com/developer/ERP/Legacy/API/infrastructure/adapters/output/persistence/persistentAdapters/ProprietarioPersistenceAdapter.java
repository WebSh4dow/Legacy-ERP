package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.persistentAdapters;

import com.developer.ERP.Legacy.API.application.ports.output.ProprietarioOutputPort;
import com.developer.ERP.Legacy.API.domain.exception.notifyException.CpfCadastradoException;
import com.developer.ERP.Legacy.API.domain.exception.notifyException.ProprietarioNotFoundException;
import com.developer.ERP.Legacy.API.domain.model.Proprietario;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ProprietarioEntity;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.mapper.ProprietarioMapper;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProprietarioPersistenceAdapter implements ProprietarioOutputPort {

    private final ProprietarioRepository repository;

    private final ProprietarioMapper proprietarioMapper;

    private final static String MSG_CPF_CADASTRADO_PROPRIETARIO = "Existe um cpf ou rg já cadastrados em nosso sistema, tente outro diferente.";

    private final static String MSG_CPF_CADASTRADO_CONJUGUE = "Existe Conjugue um cpf ou rg já cadastrados em nosso sistema, tente outro diferente.";

    private final static String MSG_PROPRIETARIO_NAO_EXISTE = "Proprietario inexistente ";

    @Override
    public Proprietario saveProprietario(Proprietario proprietario) {
        ProprietarioEntity proprietarioEntity = proprietarioMapper.toEntity(proprietario);

        if (repository.existsProprietarioByCpfAndRgIgnoreCase(proprietario.getCpf(), proprietario.getRg())) {
            throw new CpfCadastradoException(MSG_CPF_CADASTRADO_PROPRIETARIO);
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

    @Override
    public List<Proprietario> consultarProprietarioPorProfissao(String profissao) {
        return repository.consultarProprietariosPorProfissao(profissao)
                .stream().map(proprietarioMapper :: toProprietario)
                .collect(Collectors.toList());
    }

    @Override
    public Proprietario consultarProprietarioPorCpf(String cpf) {
        ProprietarioEntity proprietarioEntity = repository.consultarProprietarioPorCpf(cpf);

        if (proprietarioEntity == null) {
            throw new ProprietarioNotFoundException("A consulta atual não encontrou o usuário com o cpf informado.");
        }
        return proprietarioMapper.toProprietario(proprietarioEntity);
    }

    @Override
    public List<Proprietario> consultarProprietarioPorNome(String nome) {
        return repository.consultarProprietarioPorNome(nome)
                .stream().map(proprietarioMapper :: toProprietario)
                .collect(Collectors.toList());
    }

    @Override
    public Proprietario updateProprietario(Proprietario proprietario) {
        if (proprietario.getCodigo() == null) {
            throw new ProprietarioNotFoundException(MSG_PROPRIETARIO_NAO_EXISTE);
        }

        Optional<ProprietarioEntity> optionalProprietarioEntity = repository.findById(proprietario.getCodigo());

        if (optionalProprietarioEntity.isEmpty()) {
            throw new ProprietarioNotFoundException(MSG_PROPRIETARIO_NAO_EXISTE + proprietario.getCodigo());
        }

        ProprietarioEntity proprietarioEntity = optionalProprietarioEntity.get();
        BeanUtils.copyProperties(proprietario, proprietarioEntity);

        repository.save(proprietarioEntity);

        return proprietarioMapper.toProprietario(proprietarioEntity);
    }
}