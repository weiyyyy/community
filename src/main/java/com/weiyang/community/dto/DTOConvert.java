package com.weiyang.community.dto;

public interface DTOConvert<S,T> {
    T convert(S s);
}
