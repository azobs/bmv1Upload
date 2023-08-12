package com.c2psi.bmv1.address.errorCode;

public enum ErrorCode {
    ADDRESS_NOT_VALID(100),
    ADDRESS_DUPLICATED(200),
    ADDRESS_NOT_DELETEABLE(300),
    ADDRES_NOT_FOUND(400);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
