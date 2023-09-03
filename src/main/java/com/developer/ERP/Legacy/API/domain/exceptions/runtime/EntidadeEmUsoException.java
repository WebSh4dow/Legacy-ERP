package com.developer.ERP.Legacy.API.domain.exceptions.runtime;

public class EntidadeEmUsoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }
}
