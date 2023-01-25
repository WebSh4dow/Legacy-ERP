package com.developer.ERP.Legacy.API.domain.Repository;

import com.developer.ERP.Legacy.API.domain.Model.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecaoRepository extends JpaRepository <Secao,Long> {
}
