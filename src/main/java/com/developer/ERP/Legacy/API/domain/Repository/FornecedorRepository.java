package com.developer.ERP.Legacy.API.domain.Repository;

import com.developer.ERP.Legacy.API.domain.Model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {
}
