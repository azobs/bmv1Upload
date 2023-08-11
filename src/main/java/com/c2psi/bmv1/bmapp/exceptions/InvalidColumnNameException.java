package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class InvalidColumnNameException extends BMException{
    public InvalidColumnNameException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public InvalidColumnNameException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public InvalidColumnNameException(String message, List<String> errors) {
        super(message, errors);
    }

    public InvalidColumnNameException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public InvalidColumnNameException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public InvalidColumnNameException(String errorCode) {
        super(errorCode);
    }

    public InvalidColumnNameException(String message, String errorCode) {
        super(message, errorCode);
    }

    public InvalidColumnNameException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public InvalidColumnNameException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
