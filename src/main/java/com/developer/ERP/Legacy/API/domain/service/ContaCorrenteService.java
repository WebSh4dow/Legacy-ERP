package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.application.ports.input.CreateContaCorrenteUseCase;
import com.developer.ERP.Legacy.API.application.ports.input.GetContaCorrenteByCodigoUseCase;
import com.developer.ERP.Legacy.API.application.ports.input.GetContasCorrenteUseCase;
import com.developer.ERP.Legacy.API.application.ports.output.ContaCorrenteOutputPort;
import com.developer.ERP.Legacy.API.domain.exception.ProprietarioNotFoundException;
import com.developer.ERP.Legacy.API.domain.model.ContaCorrente;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class ContaCorrenteService implements CreateContaCorrenteUseCase, GetContasCorrenteUseCase, GetContaCorrenteByCodigoUseCase {

    private final ContaCorrenteOutputPort contaCorrenteOutputPort;

    private final static String MSG_CONTA_CORRENTE = "Não foi possivel encontrar uma conta corrente com o código:  ";

    @Override
    public ContaCorrente createContaBancaria(ContaCorrente contaCorrente) {
        return contaCorrenteOutputPort.saveContaCorrente(contaCorrente);
    }

    @Override
    public ContaCorrente getContaCorrenteByCodigo(Long codigo) {
        return contaCorrenteOutputPort.getContaCorrenteByCodigo(codigo)
                .orElseThrow(()-> new ProprietarioNotFoundException(MSG_CONTA_CORRENTE + codigo));
    }

    @Override
    public List<ContaCorrente> getContasCorrentes() {
        return contaCorrenteOutputPort.getContasCorrentes();
    }
}
