package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class MalFormedTokenException extends BMException{
    public MalFormedTokenException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public MalFormedTokenException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public MalFormedTokenException(String message, List<String> errors) {
        super(message, errors);
    }

    public MalFormedTokenException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public MalFormedTokenException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public MalFormedTokenException(String errorCode) {
        super(errorCode);
    }

    public MalFormedTokenException(String message, String errorCode) {
        super(message, errorCode);
    }

    public MalFormedTokenException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public MalFormedTokenException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
