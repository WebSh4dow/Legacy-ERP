package com.developer.ERP.Legacy.API.api.v1.controller;

import com.developer.ERP.Legacy.API.domain.model.Perfil;
import com.developer.ERP.Legacy.API.domain.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfil-cliente-fornecedor")
public class PerfilClienteController {

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping("/filtrar/por-tipo-perfil")
    public List<Perfil> filtrarPorTipoPerfil(@RequestParam String tipoPerfil) {
        return perfilRepository.filtrarPorTipoPerfil(tipoPerfil);
    }

}
