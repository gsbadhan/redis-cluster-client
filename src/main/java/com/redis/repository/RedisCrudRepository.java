package com.redis.repository;

/**
 * Basic CRUD repository.
 * 
 * @author gsingh
 *
 * @param <K>
 * @param <V>
 * @param <R>
 */
public interface RedisCrudRepository<K, V, R> {

    /**
     * check key exist
     * 
     * @param key
     * @return
     */
    public boolean isKeyExist(K key);

    /**
     * find value by key
     * 
     * @param key
     * @return
     */
    public V getValue(K key);

    /**
     * save and update key and value
     * 
     * @param key
     * @param value
     * @return
     */
    public R saveUpdate(K key, V value);

    /**
     * save and update key and value with expire time in milliseconds.
     * 
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public R saveUpdate(K key, V value, long expireTime);

    /**
     * remove key and its value
     * 
     * @param key
     * @return
     */
    public Long delete(K key);

    /**
     * Increment value
     * 
     * @param key
     * @param intVal
     * @return
     */
    public Long increment(K key, long intVal);

    /**
     * Decrement value
     * 
     * @param key
     * @param intVal
     * @return
     */
    public Long decrement(K key, long intVal);
}
