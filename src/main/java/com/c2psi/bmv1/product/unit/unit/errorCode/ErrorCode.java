package com.c2psi.bmv1.product.unit.unit.errorCode;

public enum ErrorCode {
    UNIT_NOT_VALID(401),
    UNIT_DUPLICATED(402),
    UNIT_NOT_DELETEABLE(403),
    UNIT_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
