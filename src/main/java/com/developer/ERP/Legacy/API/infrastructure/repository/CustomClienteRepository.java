package com.developer.ERP.Legacy.API.infrastructure.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteCriteriaFilter;
import com.developer.ERP.Legacy.API.domain.repository.filter.ClienteFilter;

public interface CustomClienteRepository {
	
	List<Cliente> buscarClienteCpf(String cpf);
	
	List<Cliente> buscarClienteCnpj(String cnpj);
	
	Long getClienteCount(Predicate predicate);
	
	Pageable getPageable(ClienteFilter clienteFilter);
	
	void setOrder(ClienteFilter clienteFilter,CriteriaQuery<Cliente>criteriaQuery,Root<Cliente>clientesRoot);
	
	Predicate getPredicate(ClienteCriteriaFilter clienteCriteriaFilter, Root<Cliente>clienteRoot);
	
	Page<Cliente> buscarClienteCnpjPageable(ClienteFilter clienteFilter,ClienteCriteriaFilter clienteCriteriaFilter,String cpnj);
	
	Page<Cliente> buscarClienteCpfPageable(ClienteFilter clienteFilter,ClienteCriteriaFilter clienteCriteriaFilter, String cpf);
	
	Page<Cliente> buscarClientes(ClienteFilter clienteFilter,ClienteCriteriaFilter clienteCriteriaFilter);
	
	Cliente verificarCpfCnpjCliente(Cliente cliente);
	
	
}
