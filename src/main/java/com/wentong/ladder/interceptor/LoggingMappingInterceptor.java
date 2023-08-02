package com.wentong.ladder.interceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingMappingInterceptor implements MappingInterceptor {

    @Override
    public void beforeMapping(Object source, Object target) {
        log.info("before mapping, source: {}, target: {}", source, target);
    }

    @Override
    public void afterMapping(Object source, Object target) {
        log.info("after mapping, source: {}, target: {}", source, target);
    }

    @Override
    public void exceptionMapping(Object source, Object target, Throwable e) {
        log.info("exception mapping, source: {}, target: {}, throwable: ", source, target, e);
    }
}
