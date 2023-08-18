package com.developer.ERP.Legacy.API.domain.repository;

import com.developer.ERP.Legacy.API.domain.model.Cliente;

import java.util.List;

import com.developer.ERP.Legacy.API.domain.model.Contratos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente,Long> {
	List<Cliente> findClientesById(Long id);
	
	List<Cliente>findContratosById(Long id);
}
