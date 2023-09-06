package com.c2psi.bmv1.sale.command.errorCode;

public enum ErrorCode {
    COMMAND_NOT_VALID(401),
    COMMAND_DUPLICATED(402),
    COMMAND_NOT_DELETEABLE(403),
    COMMAND_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
