package com.developer.ERP.Legacy.API.Repository;

import com.developer.ERP.Legacy.API.Model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository <Vendedor,Long> {
}
