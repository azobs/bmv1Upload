package com.c2psi.bmv1.price.baseprice.errorCode;

public enum ErrorCode {
    BASEPRICE_NOT_VALID(401),
    BASEPRICE_DUPLICATED(402),
    BASEPRICE_NOT_DELETEABLE(403),
    BASEPRICE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
