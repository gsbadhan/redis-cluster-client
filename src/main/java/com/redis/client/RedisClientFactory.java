package com.redis.client;

import redis.clients.jedis.JedisCommands;

/**
 * Use this factory for redis connection management. Note:There are many ways to
 * create client of redis.
 * 
 * @author gsingh
 *
 */
public interface RedisClientFactory {

	/**
	 * initialize connection pool.
	 */
	public void initPool();

	/**
	 * get free connection.
	 * 
	 * @return
	 */
	public <R extends JedisCommands> R getSession();

	/**
	 * shutdown all connections.
	 */
	public void shutdown();

}
