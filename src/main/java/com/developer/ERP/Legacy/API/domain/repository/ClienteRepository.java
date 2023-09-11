package com.developer.ERP.Legacy.API.domain.repository;

import com.developer.ERP.Legacy.API.domain.model.Cliente;

import java.util.List;

import com.developer.ERP.Legacy.API.domain.model.Contratos;
import com.developer.ERP.Legacy.API.domain.model.PessoaFisica;
import com.developer.ERP.Legacy.API.domain.model.PessoaJuridica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente,Long> {

	Page<Cliente> findAll(Specification<Cliente> clienteFilter, Pageable pageableTraduzido);

	

}
