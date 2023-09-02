package com.c2psi.bmv1.product.format.errorCode;

public enum ErrorCode {
    FORMAT_NOT_VALID(401),
    FORMAT_DUPLICATED(402),
    FORMAT_NOT_DELETEABLE(403),
    FORMAT_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
