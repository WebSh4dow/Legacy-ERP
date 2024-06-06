package com.developer.ERP.Legacy.API.domain.exception.modelException;

import org.springframework.http.HttpStatus;

public class ErrorBuilder {

    private static Error error;

    public ErrorBuilder() {
        error = new Error();
    }

    public static ErrorBuilder builder() {
        return new ErrorBuilder();
    }

    public ErrorBuilder withStatus(HttpStatus status) {
        error.setStatus(status);
        error.setCode(status.value());
        return this;
    }

    public ErrorBuilder withTypeError(String typeError) {
        error.setErrorType(typeError);
        return this;
    }

    public ErrorBuilder withMessage(String message) {
        error.setMessage(message);
        return this;
    }

    public ErrorBuilder withDetails(Throwable ex) {
        error.setDetail(ex);
        return this;
    }

    public Error build() {
        return error;
    }
}
