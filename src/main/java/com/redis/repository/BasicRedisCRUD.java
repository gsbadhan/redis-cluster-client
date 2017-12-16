package com.redis.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import com.redis.client.RedisClientFactory;
import com.redis.client.RedisClientFactoryImpl;
import com.redis.core.Constants;
import com.redis.core.RedisConfig;

import redis.clients.jedis.JedisCommands;

public class BasicRedisCRUD implements RedisKey<String, String> {
	protected static volatile RedisClientFactory redisClient;
	private final String namespace;

	public BasicRedisCRUD(final String namespace, final RedisConfig config) {
		this.namespace = checkNotNull(namespace);
		initRedisConfig(config);
	}

	public String getCacheKey(String... args) {
		StringBuilder keybuf = new StringBuilder(namespace);
		for (int i = 0; i < args.length; i++) {
			keybuf.append(Constants.AT_RATE).append(args[i]);
		}
		return keybuf.toString();
	}

	public JedisCommands getSession() {
		return redisClient.getSession();
	}

	private static void initRedisConfig(RedisConfig config) {
		synchronized (BasicRedisCRUD.class) {
			if (redisClient == null) {
				redisClient = new RedisClientFactoryImpl(config);
				redisClient.initPool();
			}
		}
	}

}
