package com.developer.ERP.Legacy.API.domain.repository;

import com.developer.ERP.Legacy.API.domain.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository <Vendedor,Long> {
}
