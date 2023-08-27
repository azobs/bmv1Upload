package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class JwtBearerAuthenticationException extends BMException{
    public JwtBearerAuthenticationException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public JwtBearerAuthenticationException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public JwtBearerAuthenticationException(String message, List<String> errors) {
        super(message, errors);
    }

    public JwtBearerAuthenticationException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public JwtBearerAuthenticationException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public JwtBearerAuthenticationException(String errorCode) {
        super(errorCode);
    }

    public JwtBearerAuthenticationException(String message, String errorCode) {
        super(message, errorCode);
    }

    public JwtBearerAuthenticationException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public JwtBearerAuthenticationException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
