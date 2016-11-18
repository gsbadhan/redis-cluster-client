package com.redis.repository;

public interface RedisKey<K, R> {

    public R getCacheKey(K key);
}
