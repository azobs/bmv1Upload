package com.c2psi.bmv1.sale.saleinvoice.errorCode;

public enum ErrorCode {
    SALEINVOICE_NOT_VALID(401),
    SALEINVOICE_DUPLICATED(402),
    SALEINVOICE_NOT_DELETEABLE(403),
    SALEINVOICE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
