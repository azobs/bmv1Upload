package com.c2psi.bmv1.global.exceptions;

import lombok.Data;

import java.util.List;

@Data
public class BMException extends RuntimeException{
    private List<String> errors;
    private String errorCode;

    public BMException(List<String> errors, String errorCode) {
        this.errors = errors;
        this.errorCode = errorCode;
    }

    public BMException(String message, List<String> errors, String errorCode) {
        super(message);
        this.errors = errors;
        this.errorCode = errorCode;
    }

    public BMException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause);
        this.errors = errors;
        this.errorCode = errorCode;
    }

    public BMException(Throwable cause, List<String> errors, String errorCode) {
        super(cause);
        this.errors = errors;
        this.errorCode = errorCode;
    }

    public BMException(String errorCode) {
        this.errorCode = errorCode;
    }

    public BMException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BMException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BMException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
