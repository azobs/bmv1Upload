package com.c2psi.bmv1.pos.pos.errorCode;

public enum ErrorCode {
    POINTOFSALE_NOT_VALID(401),
    POINTOFSALE_DUPLICATED(402),
    POINTOFSALE_NOT_DELETEABLE(403),
    POINTOFSALE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
