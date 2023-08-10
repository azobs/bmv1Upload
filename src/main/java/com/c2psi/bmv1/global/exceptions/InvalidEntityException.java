package com.c2psi.bmv1.global.exceptions;

import java.util.List;

public class InvalidEntityException extends BMException{
    public InvalidEntityException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public InvalidEntityException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public InvalidEntityException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public InvalidEntityException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public InvalidEntityException(String errorCode) {
        super(errorCode);
    }

    public InvalidEntityException(String message, String errorCode) {
        super(message, errorCode);
    }

    public InvalidEntityException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public InvalidEntityException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
