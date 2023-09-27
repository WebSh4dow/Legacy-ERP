package com.developer.ERP.Legacy.API.domain.exceptions.runtime;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCpfException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "CPF invalido";

    @Getter
    private final String input;
    public InvalidCpfException(String in) {
        super(String.format(DEFAULT_MESSAGE,in));
        this.input = in;
    }

    public InvalidCpfException(String message, String input) {
        super(message);
        this.input = input;
    }
}
