package com.wentong.ladder.service;

import lombok.NonNull;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedissonClient redisClient;

    public RedisService(RedissonClient redisClient) {
        this.redisClient = redisClient;
    }

    public String get(@NonNull String key) {
        return (String) redisClient.getBucket(key).get();
    }

    public void set(@NonNull String key, @NonNull String value, long timeoutSeconds) {
        redisClient.getBucket(key).set(value, timeoutSeconds, TimeUnit.SECONDS);
    }

}
