package com.fodd.entregadecomida.api.exceptionHandler;

import com.fodd.entregadecomida.domain.exception.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Erro erro = new Erro();
        List<Erro.Campo> campo = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            campo.add(new Erro.Campo(nome, mensagem));
        }

        erro.setStatus(status.value());
        erro.setDataErro(LocalDateTime.now());
        erro.setTitulo("Opss! Um ou mais campos estão inválido. Tente novamente!");
        erro.setCampo(campo);
       return handleExceptionInternal(ex, erro, headers, status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handlerDomain(DomainException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Erro erro = new Erro();
        List<Erro.Campo> campo = new ArrayList<>();
        erro.setStatus(status.value());
        erro.setDataErro(LocalDateTime.now());
        erro.setTitulo("Opss! Um ou mais campos estão inválido. Tente novamente!");
        erro.setCampo(campo);
        return handleExceptionInternal(ex,erro, new HttpHeaders(), status, request);
    }
}
