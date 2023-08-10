package com.c2psi.bmv1.userbm.enumerations;

public enum UserbmErrorCode {
    USERBM_NOT_VALID(100),
    USERBM_DUPLICATED(200),
    USERBM_NOT_DELETEABLE(300),
    USERBM_NOT_FOUND(400);
    private int errorCode;
    UserbmErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
