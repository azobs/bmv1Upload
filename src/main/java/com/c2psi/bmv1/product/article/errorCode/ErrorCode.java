package com.c2psi.bmv1.product.article.errorCode;

public enum ErrorCode {
    ARTICLE_NOT_VALID(401),
    ARTICLE_DUPLICATED(402),
    ARTICLE_NOT_DELETEABLE(403),
    ARTICLE_NOT_FOUND(404);
    private int errorCode;
    ErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
