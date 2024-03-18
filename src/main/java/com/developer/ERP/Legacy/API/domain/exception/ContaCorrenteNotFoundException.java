package com.developer.ERP.Legacy.API.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContaCorrenteNotFoundException extends RuntimeException {

    public ContaCorrenteNotFoundException(String message) {
        super(message);
    }
}
