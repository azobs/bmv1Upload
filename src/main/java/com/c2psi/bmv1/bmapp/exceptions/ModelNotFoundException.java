package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class ModelNotFoundException extends BMException{
    public ModelNotFoundException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public ModelNotFoundException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public ModelNotFoundException(String message, List<String> errors) {
        super(message, errors);
    }

    public ModelNotFoundException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public ModelNotFoundException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public ModelNotFoundException(String errorCode) {
        super(errorCode);
    }

    public ModelNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public ModelNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public ModelNotFoundException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
