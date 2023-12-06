package com.wentong.ladder.interceptor.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSONObject;
import com.wentong.ladder.interceptor.LoggingMappingInterceptor;
import com.wentong.ladder.service.RedisService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenMappingInterceptor extends LoggingMappingInterceptor {

    @Override
    public void afterMapping(Object source, Object target) {
        JSONObject tokenJSON = (JSONObject) target;
        RedisService redis = SpringUtil.getBean(RedisService.class);
        String key = tokenJSON.getString("key");
        String token = tokenJSON.getString("token");
        Long timeout = tokenJSON.getLong("timeout");
        redis.set(key, token, timeout);
        log.info("缓存 token 完成，key:{}，value:{}，失效时间:{}秒", key, token, timeout);
    }
}
