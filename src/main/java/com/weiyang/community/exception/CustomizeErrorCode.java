package com.weiyang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUSETION_NOT_FOUND("你找的问题不存在，请重新查找");


    CustomizeErrorCode(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    public String message;

    @Override
    public Integer getCode() {
        return null;
    }
}
