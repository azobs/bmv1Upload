package com.c2psi.bmv1.loading.loadingdetails.errorCode;

public enum ErrorCode {
    LOADINGDETAILS_NOT_VALID(401),
    LOADINGDETAILS_DUPLICATED(402),
    LOADINGDETAILS_NOT_DELETEABLE(403),
    LOADINGDETAILS_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
