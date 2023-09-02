package com.c2psi.bmv1.inventory.inventoryline.errorCode;

public enum ErrorCode {
    INVENTORYLINE_NOT_VALID(401),
    INVENTORYLINE_DUPLICATED(402),
    INVENTORYLINE_NOT_DELETEABLE(403),
    INVENTORYLINE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
