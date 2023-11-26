package com.developer.ERP.Legacy.API.domain.repository;

import com.developer.ERP.Legacy.API.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository <Cliente,Long>, JpaSpecificationExecutor<Cliente> {
    @Query(value = "SELECT c FROM Cliente c LEFT JOIN c.contratos co where c.id = :cliente_id and co.id = :contrato_id")
    List<Cliente> findContratosVinculateCliente(@Param("cliente_id") Long cliente_id,@Param("contrato_id") Long contrato_id);

}
