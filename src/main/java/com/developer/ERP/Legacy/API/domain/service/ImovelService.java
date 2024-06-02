package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.application.ports.input.GetHistoricoImoveisProprietarioUseCase;
import com.developer.ERP.Legacy.API.application.ports.output.ImovelOutputPort;
import com.developer.ERP.Legacy.API.domain.model.Imovel;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class ImovelService implements GetHistoricoImoveisProprietarioUseCase {

    private ImovelOutputPort outputPort;

    @Override
    public List<Imovel> historicoImoveis() {
        return outputPort.historicoImoveis();
    }
}
