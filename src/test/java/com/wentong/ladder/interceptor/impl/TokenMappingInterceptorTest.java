package com.wentong.ladder.interceptor.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSONObject;
import com.wentong.ladder.interceptor.MappingInterceptor;
import com.wentong.ladder.service.RedisService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
class TokenMappingInterceptorTest {

    @Test
    void afterMapping() {
        MappingInterceptor tokenMappingInterceptor = new TokenMappingInterceptor();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "abcdeft");
        jsonObject.put("token", "abcdeft");
        jsonObject.put("timeout", 10);
        tokenMappingInterceptor.afterMapping(null, jsonObject);
        RedisService redisService = SpringUtil.getBean(RedisService.class);
        Assertions.assertEquals("abcdeft", redisService.get("key"));
    }
}