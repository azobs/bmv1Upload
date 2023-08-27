package com.c2psi.bmv1.product.category.errorCode;

public enum ErrorCode {
    CATEGORY_NOT_VALID(401),
    CATEGORY_DUPLICATED(402),
    CATEGORY_NOT_DELETEABLE(403),
    CATEGORY_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
