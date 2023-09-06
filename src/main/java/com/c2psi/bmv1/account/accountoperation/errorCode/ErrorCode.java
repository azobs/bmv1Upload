package com.c2psi.bmv1.account.accountoperation.errorCode;

public enum ErrorCode {
    ACCOUNTOPERATION_NOT_VALID(401),
    ACCOUNTOPERATION_DUPLICATED(402),
    ACCOUNTOPERATION_NOT_DELETEABLE(403),
    ACCOUNTOPERATION_NOT_FOUND(404),
    CASHOPERATION_NOT_VALID(401),
    CASHOPERATION_DUPLICATED(402),
    CASHOPERATION_NOT_DELETEABLE(403),
    CASHOPERATION_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
