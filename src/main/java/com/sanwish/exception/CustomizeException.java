package com.sanwish.exception;

/**
 * Created by Sanwish on 2020/2/29.
 */
public class CustomizeException extends RuntimeException {

    public String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
