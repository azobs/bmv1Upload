package com.c2psi.bmv1Upload.upload.exceptions;

import java.util.List;

public class UploadDirectoriesNotCreatedException extends BMException{
    public UploadDirectoriesNotCreatedException(List<String> errors, String errorCode) {
        super(errors, errorCode);
    }

    public UploadDirectoriesNotCreatedException(String message, List<String> errors, String errorCode) {
        super(message, errors, errorCode);
    }

    public UploadDirectoriesNotCreatedException(String message, List<String> errors) {
        super(message, errors);
    }

    public UploadDirectoriesNotCreatedException(String message, Throwable cause, List<String> errors, String errorCode) {
        super(message, cause, errors, errorCode);
    }

    public UploadDirectoriesNotCreatedException(Throwable cause, List<String> errors, String errorCode) {
        super(cause, errors, errorCode);
    }

    public UploadDirectoriesNotCreatedException(String errorCode) {
        super(errorCode);
    }

    public UploadDirectoriesNotCreatedException(String message, String errorCode) {
        super(message, errorCode);
    }

    public UploadDirectoriesNotCreatedException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public UploadDirectoriesNotCreatedException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}
