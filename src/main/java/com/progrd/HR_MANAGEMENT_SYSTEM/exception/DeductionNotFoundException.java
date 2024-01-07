package com.progrd.HR_MANAGEMENT_SYSTEM.exception;

public class DeductionNotFoundException extends RuntimeException{
    public DeductionNotFoundException(Throwable cause) {
        super(cause);
    }

    public DeductionNotFoundException(String message) {
        super("deduction not found");
    }

    public DeductionNotFoundException(String message, Throwable cause) {
        super("dedction not found ", cause);
    }
}
