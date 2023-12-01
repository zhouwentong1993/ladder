package com.wentong.ladder.handler.impl;

import com.wentong.ladder.handler.AntiMappingHandler;
import com.wentong.ladder.interceptor.LoggingMappingInterceptor;
import com.wentong.ladder.interceptor.MappingInterceptor;

import java.util.Map;

/**
 * 对 HTTP 接口进行映射的过程，有些定制化东西
 * @param <S>
 * @param <T>
 */
public class AntiHttpMappingHandler<S, T> implements AntiMappingHandler<S, T> {

    private final MappingInterceptor mappingInterceptor;

    public AntiHttpMappingHandler(MappingInterceptor mappingInterceptor) {
        this.mappingInterceptor = mappingInterceptor;
    }

    public AntiHttpMappingHandler() {
        this.mappingInterceptor = new LoggingMappingInterceptor();
    }

    @Override
    public T mapping(S s, Map<String, Object> rules) {
        return null;
    }
}
