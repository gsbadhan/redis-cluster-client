package com.redis.repository;

/**
 * 
 * @author gsingh
 *
 * @param <NS>
 * @param <K>
 * @param <RET>
 */
public interface RedisKey<K, RET> {

	public RET getCacheKey(K... args);
}
