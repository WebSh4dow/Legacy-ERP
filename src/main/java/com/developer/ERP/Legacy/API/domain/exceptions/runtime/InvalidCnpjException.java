package com.developer.ERP.Legacy.API.domain.exceptions.runtime;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCnpjException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "CNPJ invalido";

    @Getter
    private final String input;
    public InvalidCnpjException(String in) {
        super(String.format(DEFAULT_MESSAGE,in));
        this.input = in;
    }

    public InvalidCnpjException(String message, String input) {
        super(message);
        this.input = input;
    }
}
