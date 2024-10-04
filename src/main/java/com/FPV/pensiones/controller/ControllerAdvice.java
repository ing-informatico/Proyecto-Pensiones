package com.FPV.pensiones.controller;


import com.FPV.pensiones.exceptions.RequestException;
import com.FPV.pensiones.model.DTO.ErrorDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    //Handle all fields validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    //Handle all request exceptions
    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ErrorDTO> handleRequestException(RequestException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO,ex.getStatus());
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ErrorDTO> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDTO> handleNoResourceFoundException(NoResourceFoundException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorDTO> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message("El método HTTP no está permitido para esta ruta.")
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<ErrorDTO> handleJpaSystemException(JpaSystemException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message("Se ha producido un error en la base de datos: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MailAuthenticationException.class)
    public ResponseEntity<ErrorDTO> handleMailAuthenticationException(MailAuthenticationException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message("Error al enviar el correo electrónico: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message("El correo ya existe!!: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
