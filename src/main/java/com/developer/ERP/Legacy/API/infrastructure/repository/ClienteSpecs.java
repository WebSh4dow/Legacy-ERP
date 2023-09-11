package com.developer.ERP.Legacy.API.infrastructure.repository;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.developer.ERP.Legacy.API.domain.model.Cliente;
import com.developer.ERP.Legacy.API.domain.repository.criteriaFilter.ClienteSpecFilter;

public class ClienteSpecs {

	public static Specification<Cliente> clienteFilter(ClienteSpecFilter clienteFilter) {
		return (root, query, builder) -> {

			ArrayList<Predicate> predicates = new ArrayList<Predicate>();

			if (clienteFilter.getId() != null) {
				predicates.add(builder.equal(root.get("cliente").get("id"), clienteFilter.getId()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
