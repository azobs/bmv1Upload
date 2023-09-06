package com.c2psi.bmv1.sale.backin.backin.errorCode;

public enum ErrorCode {
    BACKIN_NOT_VALID(401),
    BACKIN_DUPLICATED(402),
    BACKIN_NOT_DELETEABLE(403),
    BACKIN_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
