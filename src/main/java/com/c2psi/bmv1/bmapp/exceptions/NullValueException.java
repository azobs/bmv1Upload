package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class NullValueException extends BMException{
    public NullValueException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public NullValueException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public NullValueException(String message, List<String> errors) {
        super(message, errors);
    }

    public NullValueException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public NullValueException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public NullValueException(String errorCode) {
        super(errorCode);
    }

    public NullValueException(String message, String errorCode) {
        super(message, errorCode);
    }

    public NullValueException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public NullValueException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
