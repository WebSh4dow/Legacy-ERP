package com.developer.ERP.Legacy.API.application.ports.input;

import com.developer.ERP.Legacy.API.domain.model.Imovel;

import java.util.List;

public interface GetHistoricoImoveisProprietarioUseCase {

    List<Imovel> historicoImoveis();
}
