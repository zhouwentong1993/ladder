package com.wentong.ladder.interceptor;

public interface MappingInterceptor {

    void beforeMapping(Object source, Object target);

    void afterMapping(Object source, Object target);

    void exceptionMapping(Object source, Object target, Throwable e);

}
