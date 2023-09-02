package com.c2psi.bmv1.product.pf.errorCode;

public enum ErrorCode {
    PRODUCTFORMATED_NOT_VALID(401),
    PRODUCTFORMATED_DUPLICATED(402),
    PRODUCTFORMATED_NOT_DELETEABLE(403),
    PRODUCTFORMATED_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
