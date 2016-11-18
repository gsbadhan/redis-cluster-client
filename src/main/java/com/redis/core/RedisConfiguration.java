package com.redis.core;

import static com.google.common.base.Preconditions.checkNotNull;

public class RedisConfiguration {
    private final String url;
    private final String nameSpace;
    private final Integer version;

    public RedisConfiguration(final String url, final String nameSpace, final Integer version) {
        super();
        this.url = checkNotNull(url);
        this.nameSpace = checkNotNull(nameSpace);
        this.version = checkNotNull(version);
    }

    public String getUrl() {
        return url;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public Integer getVersion() {
        return version;
    }

}
