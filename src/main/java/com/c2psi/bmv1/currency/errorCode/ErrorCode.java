package com.c2psi.bmv1.currency.errorCode;

public enum ErrorCode {
    CURRENCY_NOT_VALID(100),
    CURRENCY_DUPLICATED(200),
    CURRENCY_NOT_DELETEABLE(300),
    CURRENCY_NOT_FOUND(400);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
