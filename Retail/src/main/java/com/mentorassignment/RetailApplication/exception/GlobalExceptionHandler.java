package com.mentorassignment.RetailApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleTransactionException(ProductNotFoundException ex) {
        return new ResponseEntity<>("Product Not Found: " + ex.getMessage(), HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleTransactionException(OrderNotFoundException ex) {
        return new ResponseEntity<>("Order Not Found: " + ex.getMessage(), HttpStatus.BAD_REQUEST) ;
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleTransactionException(UserNotFoundException ex) {
        return new ResponseEntity<>("Product Not Found: " + ex.getMessage(), HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("internal Error at server  side: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}