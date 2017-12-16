package com.redis.client;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.redis.core.RedisConfig;

import redis.clients.jedis.JedisCommands;

public class RedisClusterClientTestIT {

	private RedisClientFactoryImpl jedisClusterClient;

	@Before
	public void setUp() throws Exception {
		RedisConfig redisConfig = new RedisConfig();
		redisConfig.setUrls("127.0.0.1:6379");
		jedisClusterClient = new RedisClientFactoryImpl(redisConfig);
		jedisClusterClient.initPool();
	}

	@After
	public void tearDown() throws Exception {
		jedisClusterClient.shutdown();
	}

	@Test
	public void testGetSession() {
		JedisCommands jedisCommands = jedisClusterClient.getSession();
		assertTrue(jedisCommands != null);
	}

}
