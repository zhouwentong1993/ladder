package com.wentong.ladder.redis;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
@Component
public class TestRedisson {

    @Autowired
    RedissonClient redissonClient;

    @Test
    void testBasicRedis() {
        System.out.println(redissonClient.getBucket("a").get());

    }

}
