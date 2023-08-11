package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class PasswordNotConfirmedException extends BMException{
    public PasswordNotConfirmedException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public PasswordNotConfirmedException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public PasswordNotConfirmedException(String message, List<String> errors) {
        super(message, errors);
    }

    public PasswordNotConfirmedException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public PasswordNotConfirmedException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public PasswordNotConfirmedException(String errorCode) {
        super(errorCode);
    }

    public PasswordNotConfirmedException(String message, String errorCode) {
        super(message, errorCode);
    }

    public PasswordNotConfirmedException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public PasswordNotConfirmedException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
