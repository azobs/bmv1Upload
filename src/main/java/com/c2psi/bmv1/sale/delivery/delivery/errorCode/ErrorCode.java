package com.c2psi.bmv1.sale.delivery.delivery.errorCode;

public enum ErrorCode {
    DELIVERY_NOT_VALID(401),
    DELIVERY_DUPLICATED(402),
    DELIVERY_NOT_DELETEABLE(403),
    DELIVERY_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
