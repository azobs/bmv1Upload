package com.c2psi.bmv1.sale.delivery.deliverydetails.errorCode;

public enum ErrorCode {
    DELIVERYDETAILS_NOT_VALID(401),
    DELIVERYDETAILS_DUPLICATED(402),
    DELIVERYDETAILS_NOT_DELETEABLE(403),
    DELIVERYDETAILS_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
