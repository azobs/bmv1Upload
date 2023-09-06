package com.c2psi.bmv1.sale.backin.backindetails.errorCode;

public enum ErrorCode {
    BACKINDETAILS_NOT_VALID(401),
    BACKINDETAILS_DUPLICATED(402),
    BACKINDETAILS_NOT_DELETEABLE(403),
    BACKINDETAILS_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
