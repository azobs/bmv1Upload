package com.c2psi.bmv1.address.exceptions;

public enum AddressErrorCode {
    ADDRESS_NOT_VALID(100),
    ADDRESS_DUPLICATED(200),
    ADDRESS_NOT_DELETEABLE(300),
    ADDRES_NOT_FOUND(400);
    private int errorCode;
    AddressErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
