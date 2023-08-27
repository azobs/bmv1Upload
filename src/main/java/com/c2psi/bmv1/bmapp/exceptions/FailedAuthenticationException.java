package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class FailedAuthenticationException extends BMException{
    public FailedAuthenticationException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public FailedAuthenticationException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public FailedAuthenticationException(String message, List<String> errors) {
        super(message, errors);
    }

    public FailedAuthenticationException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public FailedAuthenticationException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public FailedAuthenticationException(String errorCode) {
        super(errorCode);
    }

    public FailedAuthenticationException(String message, String errorCode) {
        super(message, errorCode);
    }

    public FailedAuthenticationException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public FailedAuthenticationException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
