package com.c2psi.bmv1.bmapp.exceptions;

import java.util.List;

public class EnumNonConvertibleException extends BMException{
    public EnumNonConvertibleException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public EnumNonConvertibleException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public EnumNonConvertibleException(String message, List<String> errors) {
        super(message, errors);
    }

    public EnumNonConvertibleException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public EnumNonConvertibleException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public EnumNonConvertibleException(String errorCode) {
        super(errorCode);
    }

    public EnumNonConvertibleException(String message, String errorCode) {
        super(message, errorCode);
    }

    public EnumNonConvertibleException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public EnumNonConvertibleException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
