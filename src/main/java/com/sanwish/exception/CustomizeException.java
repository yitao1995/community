package com.sanwish.exception;

/**
 * Created by Sanwish on 2020/2/29.
 */
public class CustomizeException extends RuntimeException {

    public String message;
    public Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {

        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {

        return message;
    }

    public Integer getCode(){
        return code;
    }

}
