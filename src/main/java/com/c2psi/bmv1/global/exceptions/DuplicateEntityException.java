package com.c2psi.bmv1.global.exceptions;

import java.util.List;

public class DuplicateEntityException extends BMException{
    public DuplicateEntityException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public DuplicateEntityException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public DuplicateEntityException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public DuplicateEntityException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public DuplicateEntityException(String errorCode) {
        super(errorCode);
    }

    public DuplicateEntityException(String message, String errorCode) {
        super(message, errorCode);
    }

    public DuplicateEntityException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public DuplicateEntityException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
