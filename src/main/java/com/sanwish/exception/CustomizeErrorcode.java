package com.sanwish.exception;

/**
 * Created by Sanwish on 2020/2/29.
 */
public enum CustomizeErrorcode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你找的问题不存在了,换一个试试");

    private String message;

    CustomizeErrorcode(String message) {
        this.message = message; }

    @Override
    public String getMessage() {
        return message;
    }


}
