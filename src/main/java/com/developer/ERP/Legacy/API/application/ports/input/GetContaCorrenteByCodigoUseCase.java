package com.developer.ERP.Legacy.API.application.ports.input;

import com.developer.ERP.Legacy.API.domain.model.ContaCorrente;

public interface GetContaCorrenteByCodigoUseCase {

    ContaCorrente getContaCorrenteByCodigo(Long codigo);
}
