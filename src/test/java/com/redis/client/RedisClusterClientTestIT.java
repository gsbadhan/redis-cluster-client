package com.redis.client;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.JedisCluster;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-redis-context.xml" })
public class RedisClusterClientTestIT {
    @Inject
    private RedisClusterClient jedisClusterClient;

    @Test
    public void testGetSession() {
        JedisCluster cluster = jedisClusterClient.getSession();
        assertTrue(cluster != null);
        assertTrue(cluster.getClusterNodes().size() > 0);
    }

    @Test
    public void shutdown() {
        jedisClusterClient.shutdown();
        assertTrue(jedisClusterClient.getSession() == null);
    }

}
