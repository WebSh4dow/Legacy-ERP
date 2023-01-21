package com.developer.ERP.Legacy.API.domain.Service;

import com.developer.ERP.Legacy.API.domain.Model.Departamentos;
import com.developer.ERP.Legacy.API.domain.Repository.DepartamentoReposiotry;
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
