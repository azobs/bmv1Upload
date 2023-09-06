package com.c2psi.bmv1.account.account.errorCode;

public enum ErrorCode {
    ACCOUNT_NOT_VALID(401),
    ACCOUNT_DUPLICATED(402),
    ACCOUNT_NOT_DELETEABLE(403),
    ACCOUNT_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
