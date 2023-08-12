package com.c2psi.bmv1.pos.pos.errorCode;

public enum ErrorCode {
    POINTOFSALE_NOT_VALID(100),
    POINTOFSALE_DUPLICATED(200),
    POINTOFSALE_NOT_DELETEABLE(300),
    POINTOFSALE_NOT_FOUND(400);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
