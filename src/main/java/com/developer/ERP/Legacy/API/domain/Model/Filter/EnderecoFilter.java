package com.developer.ERP.Legacy.API.domain.Model.Filter;

import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class EnderecoFilter {

	private int pageNumber = 0;
	private int pageSize = 10;
	private Sort.Direction sortDirection = Sort.Direction.ASC;
	private String sortBy = "cep";

}
