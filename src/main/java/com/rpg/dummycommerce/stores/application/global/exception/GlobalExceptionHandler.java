package com.rpg.dummycommerce.stores.application.global.exception;

import com.rpg.dummycommerce.stores.domain.exception.CnpjAlreadyExistsException;
import com.rpg.dummycommerce.stores.domain.exception.StoreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<String> handleStoreNotFoundException(StoreNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CnpjAlreadyExistsException.class)
    public ResponseEntity<String> handleCnpjAlreadyExistsException(CnpjAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}
