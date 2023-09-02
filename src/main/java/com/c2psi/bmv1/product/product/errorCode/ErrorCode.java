package com.c2psi.bmv1.product.product.errorCode;

public enum ErrorCode {
    PRODUCT_NOT_VALID(401),
    PRODUCT_DUPLICATED(402),
    PRODUCT_NOT_DELETEABLE(403),
    PRODUCT_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
