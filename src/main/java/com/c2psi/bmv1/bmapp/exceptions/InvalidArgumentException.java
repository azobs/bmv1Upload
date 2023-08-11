package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class InvalidArgumentException extends BMException{
    public InvalidArgumentException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public InvalidArgumentException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public InvalidArgumentException(String message, List<String> errors) {
        super(message, errors);
    }

    public InvalidArgumentException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public InvalidArgumentException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public InvalidArgumentException(String errorCode) {
        super(errorCode);
    }

    public InvalidArgumentException(String message, String errorCode) {
        super(message, errorCode);
    }

    public InvalidArgumentException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public InvalidArgumentException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
