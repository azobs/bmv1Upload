package com.c2psi.bmv1.auth.errorCode;

public enum ErrorCode {

    USERBMROLE_PERMISSION_NOT_VALID(401),
    USERBMROLE_PERMISSION_DUPLICATED(402),
    USERBMROLE_PERMISSION_NOT_DELETEABLE(403),
    USERBMROLE_PERMISSION_NOT_FOUND(404);

    private int errorCode;

    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
