package com.developer.ERP.Legacy.API.domain.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class HandlerClienteCadastro extends RuntimeException {

	private static final long serialVersionUID = -7958181469369881912L;
	
	public HandlerClienteCadastro(String message) {
		super(message);
	}

}
