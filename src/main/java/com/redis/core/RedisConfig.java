package com.redis.core;

public class RedisConfig {
	private String urls;
	private boolean cluster;

	public String getUrls() {
		return urls;
	}

	/**
	 * 
	 * @param urls:
	 *            redis URLs format should like : "127.0.0.1:7000;127.0.0.1:7001"
	 */
	public void setUrls(String urls) {
		this.urls = urls;
	}

	public boolean isCluster() {
		return cluster;
	}

	public void setCluster(boolean cluster) {
		this.cluster = cluster;
	}

}
