package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository;

import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ImovelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelRepository extends JpaRepository<ImovelEntity,Long> {
}
