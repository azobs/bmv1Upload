package com.c2psi.bmv1.role.errorCode;

public enum ErrorCode {
    ROLE_NOT_VALID(401),
    ROLE_DUPLICATED(402),
    ROLE_NOT_DELETEABLE(403),
    ROLE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
