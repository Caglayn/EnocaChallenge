package com.c8n.enocachallenge.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<ExceptionMessage> handleCustomerNotFoundException(CustomerNotFoundException e){
        return ResponseEntity.status(404).body(ExceptionMessage.builder().code(404).message(e.getMessage()).build());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<ExceptionMessage> handleOrderNotFoundException(OrderNotFoundException e){
        return ResponseEntity.status(404).body(ExceptionMessage.builder().code(404).message(e.getMessage()).build());
    }
}
