package com.developer.ERP.Legacy.API.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HandlerNotFoundExceptionVendedor extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public HandlerNotFoundExceptionVendedor(String message){
        super(message);
    }
}
