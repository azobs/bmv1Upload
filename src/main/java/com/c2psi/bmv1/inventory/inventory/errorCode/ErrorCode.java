package com.c2psi.bmv1.inventory.inventory.errorCode;

public enum ErrorCode {
    INVENTORY_NOT_VALID(401),
    INVENTORY_DUPLICATED(402),
    INVENTORY_NOT_DELETEABLE(403),
    INVENTORY_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
