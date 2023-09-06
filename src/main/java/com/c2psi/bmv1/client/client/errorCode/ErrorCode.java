package com.c2psi.bmv1.client.client.errorCode;

public enum ErrorCode {
    CLIENT_NOT_VALID(401),
    CLIENT_DUPLICATED(402),
    CLIENT_NOT_DELETEABLE(403),
    CLIENT_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
