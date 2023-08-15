package com.c2psi.bmv1.auth.permission.errorCode;

public enum ErrorCode {
    PERMISSION_NOT_VALID(401),
    PERMISSION_DUPLICATED(402),
    PERMISSION_NOT_DELETEABLE(403),
    PERMISSION_NOT_FOUND(404);

    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
