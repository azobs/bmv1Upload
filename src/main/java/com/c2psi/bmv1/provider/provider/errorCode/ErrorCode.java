package com.c2psi.bmv1.provider.provider.errorCode;

public enum ErrorCode {
    PROVIDER_NOT_VALID(401),
    PROVIDER_DUPLICATED(402),
    PROVIDER_NOT_DELETEABLE(403),
    PROVIDER_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
