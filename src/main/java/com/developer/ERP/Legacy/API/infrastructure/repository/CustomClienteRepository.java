package com.developer.ERP.Legacy.API.infrastructure.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteSpecFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;

public interface CustomClienteRepository {
	

	List<Cliente> buscarClientePorId(Long id);
	
	Long getClienteCount(Predicate predicate);
	
	Pageable getPageable(ClienteFilter clienteFilter);
	
	void setOrder(ClienteFilter clienteFilter,CriteriaQuery<Cliente>criteriaQuery,Root<Cliente>clientesRoot);
	
	Predicate getPredicate(ClienteSpecFilter clienteCriteriaFilter, Root<Cliente>clienteRoot);
	
	Page<Cliente> buscarClientes(ClienteFilter clienteFilter,ClienteSpecFilter clienteCriteriaFilter);
	
	Page<Cliente> buscarClientesPorIdPageable(ClienteFilter clienteFilter, ClienteSpecFilter clienteCriteriaFilter, Long id);
		
	
}
