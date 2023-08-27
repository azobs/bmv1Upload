package com.c2psi.bmv1.product.unit.unitconversion.errorCode;

public enum ErrorCode {
    UNITCONVERSION_NOT_VALID(401),
    UNITCONVERSION_DUPLICATED(402),
    UNITCONVERSION_NOT_DELETEABLE(403),
    UNITCONVERSION_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
