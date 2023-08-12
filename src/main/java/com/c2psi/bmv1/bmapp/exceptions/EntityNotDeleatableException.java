package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class EntityNotDeleatableException extends BMException{
    public EntityNotDeleatableException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public EntityNotDeleatableException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public EntityNotDeleatableException(String message, List<String> errors) {
        super(message, errors);
    }

    public EntityNotDeleatableException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public EntityNotDeleatableException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public EntityNotDeleatableException(String errorCode) {
        super(errorCode);
    }

    public EntityNotDeleatableException(String message, String errorCode) {
        super(message, errorCode);
    }

    public EntityNotDeleatableException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public EntityNotDeleatableException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
