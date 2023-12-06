package com.wentong.ladder.redis;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class TestRedisson {

    @Autowired
    RedissonClient redissonClient;

    @Test
    void testBasicRedis() {
        Assertions.assertNull(redissonClient.getBucket(RandomUtil.randomString(12)).get());
    }

}
