package com.c2psi.bmv1.account.operation.errorCode;

public enum ErrorCode {
    OPERATION_NOT_VALID(401),
    OPERATION_DUPLICATED(402),
    OPERATION_NOT_DELETEABLE(403),
    OPERATION_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
