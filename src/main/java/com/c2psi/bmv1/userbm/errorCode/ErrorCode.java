package com.c2psi.bmv1.userbm.errorCode;

public enum ErrorCode {
    USERBM_NOT_VALID(401),
    USERBM_DUPLICATED(402),
    USERBM_NOT_DELETEABLE(403),
    USERBM_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
