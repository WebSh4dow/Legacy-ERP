package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository;

import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ContaCorrenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrenteEntity, Long> {

    boolean existsContaCorrenteByAgenciaAndNumeroContaCorrenteIgnoreCase(String agencia,String numeroContaCorrente);
}
