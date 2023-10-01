package com.c2psi.bmv1Upload.upload.exceptions;

import java.util.List;

public class UploadFileException extends BMException{
    public UploadFileException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public UploadFileException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public UploadFileException(String message, List<String> errors) {
        super(message, errors);
    }

    public UploadFileException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public UploadFileException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public UploadFileException(String errorCode) {
        super(errorCode);
    }

    public UploadFileException(String message, String errorCode) {
        super(message, errorCode);
    }

    public UploadFileException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public UploadFileException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
