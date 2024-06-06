package com.developer.ERP.Legacy.API.domain.exception.notifyException;

import com.developer.ERP.Legacy.API.domain.exception.modelException.TypeError;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class ContaCorrenteNotFoundException extends RuntimeException {

    private final TypeError typeError;

    public ContaCorrenteNotFoundException(final String message) {
        super(message);
        typeError = TypeError.INFO;
    }

    public ContaCorrenteNotFoundException(final String message, TypeError type) {
        super(message);
        this.typeError = type;
    }
}
