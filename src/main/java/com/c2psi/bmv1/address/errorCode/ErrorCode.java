package com.c2psi.bmv1.address.errorCode;

public enum ErrorCode {
    ADDRESS_NOT_VALID(401),
    ADDRESS_DUPLICATED(402),
    ADDRESS_NOT_DELETEABLE(403),
    ADDRES_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
