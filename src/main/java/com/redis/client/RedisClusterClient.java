package com.redis.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redis.core.RedisConfiguration;
import com.redis.core.Utils;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * Redis-Cluster based connection factory;
 * 
 * @author gsingh
 *
 */
public class RedisClusterClient implements RedisClientFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClusterClient.class);
    private final RedisConfiguration clusterConfiguration;
    private Set<HostAndPort> nodes;
    private JedisCluster jedisCluster;

    public RedisClusterClient(RedisConfiguration clusterConfiguration) {
        this.clusterConfiguration = checkNotNull(clusterConfiguration);
    }

    @PostConstruct
    public void initPool() {
        this.nodes = Utils.getRedisClusterNodes(this.clusterConfiguration.getUrl());
        jedisCluster = new JedisCluster(this.nodes);
        LOGGER.info("Redis Cluster physical nodes {}", this.nodes);
        LOGGER.info("Jedis cluster object {}", jedisCluster.getClusterNodes());
    }

    public JedisCluster getSession() {
        return jedisCluster;
    }

    public Set<HostAndPort> getNodes() {
        return nodes;
    }

    public boolean closeSession(Object object) {
        // TODO: NO USE AS OF NOW.
        return false;
    }

    @PreDestroy
    public void shutdown() {
        if (jedisCluster != null) {
            try {
                jedisCluster.close();
                jedisCluster = null;
            } catch (IOException e) {
                LOGGER.error("error occured in shutdown..!!", e);
            }
        }
    }

    public RedisConfiguration getConfiguration() {
        return this.clusterConfiguration;
    }

}
