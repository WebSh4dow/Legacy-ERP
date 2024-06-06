package com.developer.ERP.Legacy.API.domain.exception.handlerException;


import com.developer.ERP.Legacy.API.domain.exception.modelException.Error;
import com.developer.ERP.Legacy.API.domain.exception.modelException.ErrorBuilder;
import com.developer.ERP.Legacy.API.domain.exception.notifyException.CpfCadastradoException;
import com.developer.ERP.Legacy.API.domain.exception.notifyException.ExisteUmaContaCadastradaException;
import com.developer.ERP.Legacy.API.domain.exception.notifyException.ProprietarioNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler({ProprietarioNotFoundException.class})
    public ResponseEntity<List<Error>> handleProprietarioNotFoundException(ProprietarioNotFoundException ex) {
        List<Error> errors = List.of(ErrorBuilder.builder()
                .withStatus(HttpStatus.NOT_FOUND)
                .withTypeError(ex.getTypeError().name())
                .withMessage(ex.getMessage())
                .withDetails(ex)
                .build());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({CpfCadastradoException.class})
    public ResponseEntity<List<Error>> handleCpfCadastradoException(CpfCadastradoException ex) {
        List<Error> errors = List.of(ErrorBuilder.builder()
                .withStatus(HttpStatus.CONFLICT)
                .withTypeError(ex.getTypeError().name())
                .withMessage(ex.getMessage())
                .withDetails(ex)
                .build());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({ExisteUmaContaCadastradaException.class})
    public ResponseEntity<List<Error>> handleExisteUmaContaCadastradaException(ExisteUmaContaCadastradaException ex) {
        List<Error> errors = List.of(ErrorBuilder.builder()
                .withStatus(HttpStatus.CONFLICT)
                .withTypeError(ex.getTypeError().name())
                .withMessage(ex.getMessage())
                .withDetails(ex)
                .build());
        return ResponseEntity.badRequest().body(errors);
    }
}