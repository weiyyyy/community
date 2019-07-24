package com.weiyang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUSETION_NOT_FOUND(2001,"你找的问题不存在，请重新查找"),
    TARGET_NOT_FOUND(2002,"未选择任何问题或评论进行回复"),
    NO_LOGIN(2003,"未登录，请登录后再次尝试"),
    SYSTEM_ERROR(2004,"服务器冒烟了，请稍后重试"),
    TYPE_PARAM_ERROR(2005,"评论类型错误或不存在!"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在，请重试！"),
    COMMENT_IS_EMPTY(2007,"输入内容不能为空!");
    CustomizeErrorCode(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    private Integer code;
    private String message;
    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
