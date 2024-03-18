package com.developer.ERP.Legacy.API.application.ports.input;

import com.developer.ERP.Legacy.API.domain.model.ContaCorrente;

import java.util.List;

public interface GetContasCorrenteUseCase {
    List<ContaCorrente> getContasCorrentes();
}
