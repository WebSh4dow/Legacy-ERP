package com.developer.ERP.Legacy.API.application.ports.output;

import com.developer.ERP.Legacy.API.domain.model.Imovel;
import java.util.List;

public interface ImovelOutputPort {

    List<Imovel> historicoImoveis();
}
