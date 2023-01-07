package com.developer.ERP.Legacy.API.Repository;

import com.developer.ERP.Legacy.API.Model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {
}
