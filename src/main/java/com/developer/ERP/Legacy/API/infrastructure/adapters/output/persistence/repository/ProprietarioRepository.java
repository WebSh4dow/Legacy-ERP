package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository;

import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ProprietarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprietarioRepository extends JpaRepository<ProprietarioEntity, Long> {
}
