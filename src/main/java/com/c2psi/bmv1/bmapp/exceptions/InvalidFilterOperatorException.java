package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class InvalidFilterOperatorException extends BMException{
    public InvalidFilterOperatorException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public InvalidFilterOperatorException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public InvalidFilterOperatorException(String message, List<String> errors) {
        super(message, errors);
    }

    public InvalidFilterOperatorException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public InvalidFilterOperatorException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public InvalidFilterOperatorException(String errorCode) {
        super(errorCode);
    }

    public InvalidFilterOperatorException(String message, String errorCode) {
        super(message, errorCode);
    }

    public InvalidFilterOperatorException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public InvalidFilterOperatorException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
