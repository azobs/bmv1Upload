package com.c2psi.bmv1.client.clientspecialprice.errorCode;

public enum ErrorCode {
    CLIENTSPECIALPRICE_NOT_VALID(401),
    CLIENTSPECIALPRICE_DUPLICATED(402),
    CLIENTSPECIALPRICE_NOT_DELETEABLE(403),
    CLIENTSPECIALPRICE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
