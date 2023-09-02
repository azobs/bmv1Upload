package com.c2psi.bmv1.loading.loading.errorCode;

public enum ErrorCode {
    LOADING_NOT_VALID(401),
    LOADING_DUPLICATED(402),
    LOADING_NOT_DELETEABLE(403),
    LOADING_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
