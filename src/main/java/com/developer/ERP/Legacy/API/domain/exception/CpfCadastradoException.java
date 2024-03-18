package com.developer.ERP.Legacy.API.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CpfCadastradoException extends RuntimeException {

   public CpfCadastradoException(String message) {
        super(message);
    }
}
