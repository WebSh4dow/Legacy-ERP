package com.developer.ERP.Legacy.API.domain.exception.notifyException;

import com.developer.ERP.Legacy.API.domain.exception.modelException.TypeError;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProprietarioNotFoundException extends RuntimeException {

    private final TypeError typeError;

    public ProprietarioNotFoundException(final String message) {
        super(message);
        typeError = TypeError.INFO;
    }

    public ProprietarioNotFoundException(final String message, TypeError type) {
        super(message);
        this.typeError = type;
    }

}
