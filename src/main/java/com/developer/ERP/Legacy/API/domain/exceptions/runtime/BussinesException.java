package com.developer.ERP.Legacy.API.domain.exceptions.runtime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BussinesException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public BussinesException(String message) {

		super(message);
	}

	public BussinesException(String message, Throwable e) {

		super(message, e);
	}

}
