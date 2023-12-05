package com.wentong.ladder.interceptor.impl;

import com.alibaba.fastjson2.JSONObject;
import com.wentong.ladder.interceptor.LoggingMappingInterceptor;

public class TokenMappingInterceptor extends LoggingMappingInterceptor {

    @Override
    public void afterMapping(Object source, Object target) {
        JSONObject tokenJSON = (JSONObject) target;
    }
}
