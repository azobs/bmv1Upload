package com.c2psi.bmv1.loading.packagingdetails.errorCode;

public enum ErrorCode {
    PACKAGINGDETAILS_NOT_VALID(401),
    PACKAGINGDETAILS_DUPLICATED(402),
    PACKAGINGDETAILS_NOT_DELETEABLE(403),
    PACKAGINGDETAILS_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
