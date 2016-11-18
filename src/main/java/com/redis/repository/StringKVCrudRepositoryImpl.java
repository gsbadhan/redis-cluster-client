package com.redis.repository;

import com.redis.client.RedisClientFactory;

public class StringKVCrudRepositoryImpl extends BasicRedisCRUD implements RedisCrudRepository<String, String, String> {

    public StringKVCrudRepositoryImpl(RedisClientFactory redisClusterClient) {
        super(redisClusterClient);
    }

    public String saveUpdate(String key, String value) {
        return redisClusterClient.getSession().set(getCacheKey(key), value);
    }

    public String saveUpdate(String key, String value, long expireTime) {
        return redisClusterClient.getSession().psetex(getCacheKey(key), expireTime, value);
    }

    public Long delete(String key) {
        return redisClusterClient.getSession().del(getCacheKey(key));
    }

    public String getValue(String key) {
        return redisClusterClient.getSession().get(getCacheKey(key));
    }

    public Long increment(String key, long intVal) {
        return redisClusterClient.getSession().incrBy(getCacheKey(key), intVal);
    }

    public Long decrement(String key, long intVal) {
        return redisClusterClient.getSession().decrBy(getCacheKey(key), intVal);
    }

    public boolean isKeyExist(String key) {
        return redisClusterClient.getSession().exists(getCacheKey(key));
    }

}
