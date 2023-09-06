package com.c2psi.bmv1.sale.sale.errorCode;

public enum ErrorCode {
    SALE_NOT_VALID(401),
    SALE_DUPLICATED(402),
    SALE_NOT_DELETEABLE(403),
    SALE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
