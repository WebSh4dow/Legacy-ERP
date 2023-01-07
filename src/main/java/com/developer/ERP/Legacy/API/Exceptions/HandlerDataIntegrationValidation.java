package com.developer.ERP.Legacy.API.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class HandlerDataIntegrationValidation extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public HandlerDataIntegrationValidation(String message){
        super(message);
    }

}
