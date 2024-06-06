package com.developer.ERP.Legacy.API.application.ports.input;

import com.developer.ERP.Legacy.API.domain.model.Proprietario;

import java.util.List;

public interface GetProprietarioPorNomeUseCase {
    List<Proprietario> consultarProprietarioPorNome(String nome);
}
