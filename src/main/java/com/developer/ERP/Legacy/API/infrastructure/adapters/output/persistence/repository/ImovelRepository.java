package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.repository;

import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity.ImovelEntity;
import com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.query.ImovelQuerys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImovelRepository extends JpaRepository<ImovelEntity,Long>, ImovelQuerys {

    @Query(nativeQuery = true, value = HISTORICO_IMOVEIS_QUERY)
    List<ImovelEntity> historicoImoveis();

}
