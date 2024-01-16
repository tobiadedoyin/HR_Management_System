package com.progrd.HR_MANAGEMENT_SYSTEM.exception;

import org.springframework.http.HttpStatus;

public class PaymentNotfoundException extends java.lang.Exception {
    public PaymentNotfoundException(String message) {
        super(message);
    }

    public PaymentNotfoundException(String message, Throwable cause) {
        super(message, cause);
    }
}