package com.progrd.HR_MANAGEMENT_SYSTEM.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionHandler {

     @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> responseBuilder(PaymentNotFoundException paymentNotFoundException){
      Exception exception= new Exception(paymentNotFoundException.getMessage(), paymentNotFoundException.getCause(),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(paymentNotFoundException, HttpStatus.NOT_FOUND);

    }

}
