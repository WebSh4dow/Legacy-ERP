package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.application.ports.input.CreateProprietarioUseCase;
import com.developer.ERP.Legacy.API.application.ports.input.GetProprietarioByCodigoUseCase;
import com.developer.ERP.Legacy.API.application.ports.input.GetProprietariosUseCase;
import com.developer.ERP.Legacy.API.application.ports.output.ProprietarioOutputPort;
import com.developer.ERP.Legacy.API.domain.exception.ProprietarioNotFoundException;
import com.developer.ERP.Legacy.API.domain.model.Proprietario;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProprietarioService implements CreateProprietarioUseCase, GetProprietarioByCodigoUseCase, GetProprietariosUseCase {

    private final ProprietarioOutputPort proprietarioOutputPort;
    @Override
    public Proprietario createProprietario(Proprietario proprietario) {
        return proprietarioOutputPort.saveProprietario(proprietario);
    }

    @Override
    public Proprietario getProprietarioByCodigo(Long codigo) {
        return proprietarioOutputPort.getProprietarioByCodigo(codigo)
                .orElseThrow(()-> new ProprietarioNotFoundException("Não foi possivel encontrar o proprietario com o codigo atual:  " + codigo));
    }

    @Override
    public List<Proprietario> getProprietarios() {
        return proprietarioOutputPort.getProprietarios();
    }
}
