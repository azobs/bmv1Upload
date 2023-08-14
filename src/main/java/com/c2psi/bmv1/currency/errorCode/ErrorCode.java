package com.c2psi.bmv1.currency.errorCode;

public enum ErrorCode {
    CURRENCY_NOT_VALID(401),
    CURRENCY_DUPLICATED(402),
    CURRENCY_NOT_DELETEABLE(403),
    CURRENCY_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
