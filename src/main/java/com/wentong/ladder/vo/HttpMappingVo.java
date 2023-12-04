package com.wentong.ladder.vo;

/**
 * HTTP 映射标准对象
 */
public class HttpMappingVo<T> {
    public boolean success;
    public String message;
    public T data;
}
