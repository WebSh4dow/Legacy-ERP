package com.developer.ERP.Legacy.API.api.exceptionhandler;

import com.developer.ERP.Legacy.API.domain.exceptions.response.ProblemApi;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.BussinesException;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.EntidadeEmUsoException;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.HandlerNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.developer.ERP.Legacy.API.domain.exceptions.types.ProblemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.nio.file.AccessDeniedException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    public static final String MSG_ERRO_SISTEMA_GENERICO = "Ocorreu um erro interno inesperado no sistema. Porfavor tente novamente"
            + "se o problema persistir, entre em contato com o suporte informando o ocorrido para que possamos resolver rapidamente o problema.";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null)
            body = ProblemApi.builder()
                    .timestamp(OffsetDateTime.now())
                    .title(HttpStatus.valueOf(status.value()).getReasonPhrase())
                    .status(status.value())
                    .message(MSG_ERRO_SISTEMA_GENERICO)
                    .build();
        else if (body instanceof String)
            body = ProblemApi.builder()
                    .timestamp(OffsetDateTime.now())
                    .title(HttpStatus.valueOf(status.value()).getReasonPhrase())
                    .status(status.value())
                    .message(MSG_ERRO_SISTEMA_GENERICO)
                    .build();

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status).headers(headers).build();
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String message = String.format("O recurso %s, que você tentou acessar, não existe.",ex.getRequestURL());

        ProblemApi problemApi = createProblemBuilder(status,problemType,message)
                .message(MSG_ERRO_SISTEMA_GENERICO)
                .build();

        return handleExceptionInternal(ex,problemApi,headers,status,request);
    }
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException)
            return handleMethodArgumentTypeMismatch(
                    (MethodArgumentTypeMismatchException) ex, headers, status, request);
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException)
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        else if (rootCause instanceof PropertyBindingException)
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);

        ProblemType problemType = ProblemType.MENSAGEM_NAO_COMPREENSIVEL;
        String message = "O corpo da requisição não é um corpo valido. Verifique erro de digitação.";

        ProblemApi problemApi = createProblemBuilder(status,problemType,message)
                .message(MSG_ERRO_SISTEMA_GENERICO)
                .build();

        return handleExceptionInternal(ex, problemApi, headers, status, request);
    }
    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException rootCause, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(rootCause.getPath());

        ProblemType problemType = ProblemType.MENSAGEM_NAO_COMPREENSIVEL;
        String mensagem = String.format("A propriedade '%s' não existe. Porfavor corrija essa propriedade e tente outra vez.",path);

        ProblemApi problemApi = createProblemBuilder(status, problemType, mensagem)
                .message(MSG_ERRO_SISTEMA_GENERICO)
                .build();

        return handleExceptionInternal(rootCause, problemApi, headers, status, request);
    }
    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException rootCause, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(rootCause.getPath());

        ProblemType problemType = ProblemType.MENSAGEM_NAO_COMPREENSIVEL;
        String message = String.format("A propriedade '%s' recebeu um valor '%s',"
        + "que é de um tipo invalido. Corrija porfavor o valor inserido para um tipo compatível.",
                path,rootCause.getValue(),rootCause.getTargetType().getSimpleName());

        ProblemApi problemApi = createProblemBuilder(status,problemType,message)
                .message(MSG_ERRO_SISTEMA_GENERICO)
                .build();

        return handleExceptionInternal(rootCause, problemApi, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String message = "O parâmetro da URL  recebeu um valor " +
                "que é um tipo de valor inválido reconhecido na requesição." + "Ajuste e informe um valor compatível com o tipo de valor informado.";

        ProblemApi problemApi = createProblemBuilder(status,problemType,message)
                .message(MSG_ERRO_SISTEMA_GENERICO)
                .build();
        return handleExceptionInternal(ex, problemApi, headers, status, request);
    }
    private ResponseEntity<Object> handleValidationInternal(Exception exception, HttpHeaders httpHeaders,
                                                            HttpStatus httpStatus, WebRequest webRequest,
                                                            BindingResult bindingResult){
        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        String detailsMessage = "Um ou mais campos estão inválidos. Faça o preenchimento correto porfavor.";

        List<ProblemApi.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError)
                        name = ((FieldError) objectError).getField();
                    return ProblemApi.Object.builder()
                            .name(name)
                            .message(message)
                            .build();
                }).collect(Collectors.toList());

        ProblemApi problemApi = createProblemBuilder(httpStatus,problemType,detailsMessage)
                .message(detailsMessage)
                .objects(problemObjects)
                .build();
        return handleExceptionInternal(exception,problemApi,httpHeaders,httpStatus,webRequest);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERRO_INTERNO;
        String messageDetails = MSG_ERRO_SISTEMA_GENERICO;

        ProblemApi problemApi = createProblemBuilder(status,problemType,messageDetails)
                .message(messageDetails)
                .build();

        return  handleExceptionInternal(ex,problemApi,new HttpHeaders(),status,request);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(AccessDeniedException exception, WebRequest webRequest){

        HttpStatus status = HttpStatus.FORBIDDEN;
        ProblemType problemType = ProblemType.ACESSO_NEGADO_USUARIO;

        String message = exception.getMessage();

        ProblemApi problemApi = createProblemBuilder(status,problemType,message)
                .message(message)
                .message("Você não possui permissão para acessar esse recurso.")
                .build();

        return handleExceptionInternal(exception, problemApi, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(HandlerNotFoundException.class)
    public ResponseEntity<?>handleEntidadeNaoEncontrada(HandlerNotFoundException exception, WebRequest webRequest){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;

        String message = exception.getMessage();

        ProblemApi problemApi = createProblemBuilder(status,problemType,message)
                .message(message)
                .build();

        return handleExceptionInternal(exception, problemApi, new HttpHeaders(), status, webRequest);
    }
    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException exception, WebRequest webRequest){
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;

        String message = exception.getMessage();

        ProblemApi problemApi = createProblemBuilder(status,problemType,message)
                .message(message)
                .build();

        return handleExceptionInternal(exception, problemApi, new HttpHeaders(), status, webRequest);
    }
    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<?> handleBussines(BussinesException exception, WebRequest webRequest){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_DE_NEGOCIO;

        String message = exception.getMessage();

        ProblemApi problemApi = createProblemBuilder(status,problemType,message)
                .message(message)
                .build();

        return handleExceptionInternal(exception, problemApi, new HttpHeaders(), status, webRequest);

    }

    private ProblemApi.ProblemApiBuilder createProblemBuilder(HttpStatus httpStatus, ProblemType problemType, String detailsMessage) {
        return ProblemApi.builder()
                .timestamp(OffsetDateTime.now())
                .status(httpStatus.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detailsMessage);
    }
    private String joinPath(List<Reference>references){
        return references.stream()
                .map(Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

}