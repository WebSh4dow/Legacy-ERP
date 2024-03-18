package com.developer.ERP.Legacy.API.application.ports.output;

import com.developer.ERP.Legacy.API.domain.model.Proprietario;
import java.util.List;
import java.util.Optional;

public interface ProprietarioOutputPort {

    Proprietario saveProprietario(Proprietario proprietario);
    Optional<Proprietario> getProprietarioByCodigo(Long codigo);
    List<Proprietario> getProprietarios();
}
