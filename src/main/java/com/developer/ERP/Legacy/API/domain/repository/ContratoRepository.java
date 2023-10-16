package com.developer.ERP.Legacy.API.domain.repository;

import com.developer.ERP.Legacy.API.domain.model.Contratos;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ContratoRepository extends JpaRepository<Contratos,Long>, JpaSpecificationExecutor<Contratos> {
    Optional<Contratos> findContratosById(Long id);
}
