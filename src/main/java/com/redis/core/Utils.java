package com.redis.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.HostAndPort;

public class Utils {

	public static Set<HostAndPort> getRedisClusterNodes(String url) {
		String ipAndPort[] = url.split(Constants.SEMICOLON);
		if (ipAndPort == null || ipAndPort.length <= 0) {
			return Collections.emptySet();
		}

		Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
		for (String host : ipAndPort) {
			String hostAndPort[] = host.trim().split(Constants.COLON);
			if (hostAndPort == null || hostAndPort.length <= 1) {
				continue;
			}
			hostAndPorts.add(new HostAndPort(hostAndPort[0].trim(), Integer.parseInt(hostAndPort[1].trim())));
		}
		return hostAndPorts;
	}

	public static long getMilliSeconds(long delay, TimeUnit timeUnit) {
		long delayTime = 0;
		switch (timeUnit) {
		case MILLISECONDS:
			delayTime = delay;
			break;
		case SECONDS:
			delayTime = delay * 1000;
			break;
		case MINUTES:
			delayTime = delay * (60 * 1000);
			break;
		case HOURS:
			delayTime = delay * (60 * 60 * 1000);
			break;
		case DAYS:
			delayTime = delay * (24 * 60 * 60 * 1000);
			break;
		default:
			delayTime = 0;
			break;
		}
		return delayTime;
	}

}
