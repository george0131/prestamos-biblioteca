package com.serfinanzas.prestamos.rest;

import com.serfinanzas.prestamos.rest.domain.ErrorMessage;
import com.serfinanzas.prestamos.service.exception.LendException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class CommandRestController {

    @ExceptionHandler(value = LendException.class)
    public ResponseEntity<ErrorMessage> trackvyException(LendException ex) {
        return new ResponseEntity<>(
                new ErrorMessage(ex.getMessage(), ex.getStatusCode()),
                HttpStatus.valueOf(ex.getStatusCode())
        );
    }
}
