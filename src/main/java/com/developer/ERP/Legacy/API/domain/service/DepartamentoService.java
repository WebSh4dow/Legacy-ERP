package com.developer.ERP.Legacy.API.domain.service;

import com.developer.ERP.Legacy.API.domain.model.Departamentos;
import com.developer.ERP.Legacy.API.domain.repository.DepartamentoReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoReposiotry departamentoReposiotry;

    public ResponseEntity<Departamentos> buscar(Long id){
        Departamentos buscarDepartamentos = departamentoReposiotry.findById(id)
                .orElse(null);
        return ResponseEntity.ok().body(buscarDepartamentos);
    }
}
