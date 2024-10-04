package com.FPV.pensiones.exceptions;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RequestException extends RuntimeException {
    private HttpStatus status;
    public RequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
