package com.developer.ERP.Legacy.API.application.ports.output;

import com.developer.ERP.Legacy.API.domain.model.ContaCorrente;
import java.util.List;
import java.util.Optional;

public interface ContaCorrenteOutputPort {

    ContaCorrente saveContaCorrente(ContaCorrente contaCorrente);
    Optional<ContaCorrente> getContaCorrenteByCodigo(Long codigo);
    List<ContaCorrente> getContasCorrentes();
}
