package com.c2psi.bmv1.price.specialprice.errorCode;

public enum ErrorCode {
    SPECIALPRICE_NOT_VALID(401),
    SPECIALPRICE_DUPLICATED(402),
    SPECIALPRICE_NOT_DELETEABLE(403),
    SPECIALPRICE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
