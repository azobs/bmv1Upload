package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class InvalidDirectionofMouvementException extends BMException{
    public InvalidDirectionofMouvementException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public InvalidDirectionofMouvementException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public InvalidDirectionofMouvementException(String message, List<String> errors) {
        super(message, errors);
    }

    public InvalidDirectionofMouvementException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public InvalidDirectionofMouvementException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public InvalidDirectionofMouvementException(String errorCode) {
        super(errorCode);
    }

    public InvalidDirectionofMouvementException(String message, String errorCode) {
        super(message, errorCode);
    }

    public InvalidDirectionofMouvementException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public InvalidDirectionofMouvementException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
