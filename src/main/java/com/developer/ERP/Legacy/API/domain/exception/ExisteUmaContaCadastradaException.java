package com.developer.ERP.Legacy.API.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExisteUmaContaCadastradaException extends RuntimeException{
    public ExisteUmaContaCadastradaException(String message) {
        super(message);
    }
}
