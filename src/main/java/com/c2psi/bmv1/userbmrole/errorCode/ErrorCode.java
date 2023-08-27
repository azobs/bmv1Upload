package com.c2psi.bmv1.userbmrole.errorCode;

public enum ErrorCode {
    USERBMROLE_NOT_VALID(401),
    USERBMROLE_DUPLICATED(402),
    USERBMROLE_NOT_DELETEABLE(403),
    USERBMROLE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
