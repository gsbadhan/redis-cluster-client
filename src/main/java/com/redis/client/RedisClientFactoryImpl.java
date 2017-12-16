package com.redis.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redis.core.RedisConfig;
import com.redis.core.Utils;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

/**
 * Redis connection factory;
 * 
 * @author gsingh
 *
 */
public class RedisClientFactoryImpl implements RedisClientFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisClientFactoryImpl.class);
	private final RedisConfig redisConfig;
	private Set<HostAndPort> nodes;
	private JedisCluster jedisCluster;
	private Jedis jedis;
	private JedisCommands jedisCommands;

	public RedisClientFactoryImpl(RedisConfig redisConfig) {
		this.redisConfig = checkNotNull(redisConfig);
	}

	public void initPool() {
		this.nodes = Utils.getRedisClusterNodes(this.redisConfig.getUrls());
		if (redisConfig.isCluster()) {
			jedisCluster = new JedisCluster(this.nodes);
			LOGGER.info("Jedis cluster object {}", jedisCluster.getClusterNodes());
			jedisCommands = jedisCluster;
		} else {
			HostAndPort hostAndPort = (HostAndPort) nodes.toArray()[0];
			jedis = new Jedis(hostAndPort.getHost(), hostAndPort.getPort());
			LOGGER.info("Redis simple client loaded:{}", hostAndPort);
			jedisCommands = jedis;
		}

	}

	@SuppressWarnings("unchecked")
	public JedisCommands getSession() {
		return jedisCommands;
	}

	public Set<HostAndPort> getNodes() {
		return nodes;
	}

	public void shutdown() {
		if (redisConfig.isCluster()) {
			try {
				jedisCluster.close();
			} catch (IOException e) {
				LOGGER.error("getting error while shuting down cluster:", e);
			}
		} else {
			jedis.close();
		}
	}

}
