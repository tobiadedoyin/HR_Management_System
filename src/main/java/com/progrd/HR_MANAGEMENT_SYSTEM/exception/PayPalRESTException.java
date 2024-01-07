package com.progrd.HR_MANAGEMENT_SYSTEM.exception;

public class PayPalRESTException extends RuntimeException {


        // Fields to store error details
        private String errorCode;
        private String message;
        private String details;

        // Constructors to initialize fields
        public PayPalRESTException(String message) {
            super(message);
        }

        public PayPalRESTException(String message, Throwable cause) {
            super(message, cause);
        }

        public PayPalRESTException(String errorCode, String message, String details) {
            this.errorCode = errorCode;
            this.message = message;
            this.details = details;
        }

        // Getters and setters for error details
        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }

