package com.c2psi.bmv1.arrival.arrival.errorCode;

public enum ErrorCode {
    ARRIVAL_NOT_VALID(401),
    ARRIVAL_DUPLICATED(402),
    ARRIVAL_NOT_DELETEABLE(403),
    ARRIVAL_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
