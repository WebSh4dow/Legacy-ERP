package com.developer.ERP.Legacy.API.Repository;

import com.developer.ERP.Legacy.API.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente,Long> {
}
