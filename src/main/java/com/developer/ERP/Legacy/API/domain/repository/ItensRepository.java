package com.developer.ERP.Legacy.API.domain.repository;

import com.developer.ERP.Legacy.API.domain.model.Itens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensRepository extends JpaRepository <Itens,Long> {
}
