package com.redis.repository;

import com.redis.core.RedisConfig;

public class StringKVCrudRepositoryImpl extends BasicRedisCRUD implements RedisCrudRepository<String, String> {

	public StringKVCrudRepositoryImpl(String namespace, RedisConfig redisConfig) {
		super(namespace, redisConfig);
	}

	public String saveUpdate(String key, String value) {
		return getSession().set(getCacheKey(key), value);
	}

	public String saveUpdate(String key, String value, long expireTime) {
		return getSession().psetex(getCacheKey(key), expireTime, value);
	}

	public Long delete(String key) {
		return getSession().del(getCacheKey(key));
	}

	public String getValue(String key) {
		return getSession().get(getCacheKey(key));
	}

	public Long increment(String key, long intVal) {
		return getSession().incrBy(getCacheKey(key), intVal);
	}

	public Long decrement(String key, long intVal) {
		return getSession().decrBy(getCacheKey(key), intVal);
	}

	public boolean isKeyExist(String key) {
		return getSession().exists(getCacheKey(key));
	}

}
