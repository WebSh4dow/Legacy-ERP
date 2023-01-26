package com.developer.ERP.Legacy.API.domain.Repository;

import com.developer.ERP.Legacy.API.domain.Model.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportadoraRepository extends JpaRepository <Transportadora,Long> {
}
