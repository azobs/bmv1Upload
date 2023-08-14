package com.c2psi.bmv1.pos.enterprise.errorCode;

public enum ErrorCode {
    ENTERPRISE_NOT_VALID(401),
    ENTERPRISE_DUPLICATED(402),
    ENTERPRISE_NOT_DELETEABLE(403),
    ENTERPRISE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
