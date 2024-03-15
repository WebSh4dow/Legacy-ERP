package com.developer.ERP.Legacy.API.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProprietarioNotFoundException extends RuntimeException{

    public ProprietarioNotFoundException(String message) {
        super(message);
    }
}
