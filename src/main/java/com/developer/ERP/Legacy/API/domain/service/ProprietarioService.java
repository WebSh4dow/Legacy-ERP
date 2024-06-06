package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.application.ports.input.*;
import com.developer.ERP.Legacy.API.application.ports.output.ProprietarioOutputPort;
import com.developer.ERP.Legacy.API.domain.exception.notifyException.ProprietarioNotFoundException;
import com.developer.ERP.Legacy.API.domain.model.Proprietario;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class ProprietarioService implements CreateProprietarioUseCase, GetProprietarioByCodigoUseCase, GetProprietariosUseCase,
        GetProprietarioPorNomeUseCase,
        GetProprietariosPorCpfUseCase,
        GetProprietariosPorProfissaoUseCase,
        UpdateProprietarioUseCase {

    private final ProprietarioOutputPort proprietarioOutputPort;

    private final static String MSG_CODIGO_PROPRIETARIO = "Não foi possivel encontrar o proprietario com o codigo atual: ";

    @Override
    public Proprietario createProprietario(Proprietario proprietario) {
        return proprietarioOutputPort.saveProprietario(proprietario);
    }

    @Override
    public Proprietario getProprietarioByCodigo(Long codigo) {
        return proprietarioOutputPort.getProprietarioByCodigo(codigo)
                .orElseThrow(()-> new ProprietarioNotFoundException(MSG_CODIGO_PROPRIETARIO + codigo));
    }

    @Override
    public List<Proprietario> getProprietarios() {
        return proprietarioOutputPort.getProprietarios();
    }

    @Override
    public Proprietario updateProprietario(Proprietario proprietario) {
        return proprietarioOutputPort.updateProprietario(proprietario);
    }

    @Override
    public List<Proprietario> consultarProprietarioPorNome(String nome) {
        return proprietarioOutputPort.consultarProprietarioPorNome(nome);
    }

    @Override
    public List<Proprietario> consultarProprietarioPorProfissao(String profissao) {
        return proprietarioOutputPort.consultarProprietarioPorProfissao(profissao);
    }

    @Override
    public Proprietario consultarProprietarioPorCpf(String cpf) {
        return proprietarioOutputPort.consultarProprietarioPorCpf(cpf);
    }
}
