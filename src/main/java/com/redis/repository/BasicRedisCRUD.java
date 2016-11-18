package com.redis.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import com.redis.client.RedisClientFactory;
import com.redis.core.Constants;

public class BasicRedisCRUD implements RedisKey<String, String> {
    protected final RedisClientFactory redisClusterClient;

    public BasicRedisCRUD(RedisClientFactory redisClusterClient) {
        this.redisClusterClient = checkNotNull(redisClusterClient);
        checkNotNull(redisClusterClient.getConfiguration());
    }

    /**
     * return key format : (name_space):(ns_sequence)@(key).
     */
    public String getCacheKey(String key) {
        String cachekey = redisClusterClient.getConfiguration().getNameSpace() + Constants.COLON
                + redisClusterClient.getConfiguration().getVersion() + Constants.AT_RATE + key;
        return cachekey;
    }

}
