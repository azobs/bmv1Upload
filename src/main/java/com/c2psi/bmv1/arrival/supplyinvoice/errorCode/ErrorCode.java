package com.c2psi.bmv1.arrival.supplyinvoice.errorCode;

public enum ErrorCode {
    SUPPLYINVOICE_NOT_VALID(401),
    SUPPLYINVOICE_DUPLICATED(402),
    SUPPLYINVOICE_NOT_DELETEABLE(403),
    SUPPLYINVOICE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
