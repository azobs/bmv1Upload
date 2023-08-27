package com.c2psi.bmv1.auth.token.errorCode;

public enum ErrorCode {
    TOKEN_NOT_VALID(401),
    TOKEN_DUPLICATED(402),
    TOKEN_NOT_DELETEABLE(403),
    TOKEN_NOT_FOUND(404);

    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
