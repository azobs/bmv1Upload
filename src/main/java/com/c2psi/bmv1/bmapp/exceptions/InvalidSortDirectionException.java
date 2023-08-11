package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class InvalidSortDirectionException extends BMException{
    public InvalidSortDirectionException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public InvalidSortDirectionException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public InvalidSortDirectionException(String message, List<String> errors) {
        super(message, errors);
    }

    public InvalidSortDirectionException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public InvalidSortDirectionException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public InvalidSortDirectionException(String errorCode) {
        super(errorCode);
    }

    public InvalidSortDirectionException(String message, String errorCode) {
        super(message, errorCode);
    }

    public InvalidSortDirectionException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public InvalidSortDirectionException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
