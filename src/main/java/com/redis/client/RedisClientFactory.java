package com.redis.client;

import com.redis.core.RedisConfiguration;

import redis.clients.jedis.JedisCommands;

/**
 * Use this factory for redis connection management. Note:There are many ways to create client of redis.
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
     * close open connection.
     * 
     * @return
     */
    public <C> boolean closeSession(C c);

    /**
     * shutdown all connections.
     */
    public void shutdown();

    public RedisConfiguration getConfiguration();
}
