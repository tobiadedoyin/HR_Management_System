package com.progrd.HR_MANAGEMENT_SYSTEM.exception;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String message) {
        super(message);
    }

    public DepartmentNotFoundException() {
        super("Department not found");
    }
}
