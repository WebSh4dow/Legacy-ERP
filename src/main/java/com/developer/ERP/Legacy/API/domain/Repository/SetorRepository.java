package com.developer.ERP.Legacy.API.domain.Repository;

import com.developer.ERP.Legacy.API.domain.Model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository  extends JpaRepository<Setor,Long> {
}
