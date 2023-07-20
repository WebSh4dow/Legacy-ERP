package com.developer.ERP.Legacy.API.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developer.ERP.Legacy.API.domain.Model.ReferenciasComerciais;

@Repository
public interface ReferenciaHistoricoRepository extends JpaRepository<ReferenciasComerciais, Long> {
	
	

}
