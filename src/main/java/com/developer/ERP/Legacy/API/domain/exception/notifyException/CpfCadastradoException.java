package com.developer.ERP.Legacy.API.domain.exception.notifyException;

import com.developer.ERP.Legacy.API.domain.exception.modelException.TypeError;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class CpfCadastradoException extends RuntimeException {

    private final TypeError typeError;

    public CpfCadastradoException(final String message) {
        super(message);
        typeError = TypeError.ERROR;
    }

    public CpfCadastradoException(final String message, TypeError type) {
        super(message);
        this.typeError = type;
    }
}
