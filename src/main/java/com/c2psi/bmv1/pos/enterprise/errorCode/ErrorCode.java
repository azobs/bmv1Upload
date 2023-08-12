package com.c2psi.bmv1.pos.enterprise.errorCode;

public enum ErrorCode {
    ENTERPRISE_NOT_VALID(100),
    ENTERPRISE_DUPLICATED(200),
    ENTERPRISE_NOT_DELETEABLE(300),
    ENTERPRISE_NOT_FOUND(400);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
