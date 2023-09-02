package com.c2psi.bmv1.packaging.packaging.errorCode;

public enum ErrorCode {
    PACKAGING_NOT_VALID(401),
    PACKAGING_DUPLICATED(402),
    PACKAGING_NOT_DELETEABLE(403),
    PACKAGING_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
