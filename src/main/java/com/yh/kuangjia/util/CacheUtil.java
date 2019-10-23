package com.yh.kuangjia.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheUtil {
    @Autowired
    private CacheManager cacheManager;

    private CacheUtil() {
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 设置值
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value) {
        Cache cache = cacheManager.getCache(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
    }

    /**
     * 获取值
     *
     * @param cacheName
     * @param key
     * @return
     */
    public <T> T get(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : (T) element.getObjectValue();
    }


    /**
     * 获取值，如果值不存在则存入值
     *
     * @param cacheName
     * @param key
     * @param onCacheEmpty
     * @return
     */
    public <T> T get(String cacheName, String key, OnCacheEmpty onCacheEmpty) {
        Cache cache = cacheManager.getCache(cacheName);
        Element element = cache.get(key);
        if (element == null) {
            element = new Element(key, onCacheEmpty.getCacheData());
            cache.put(element);
        }
        return (T) element.getObjectValue();
    }

    /**
     * 删除值
     *
     * @param cacheName
     * @param key
     */
    public void remove(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        cache.remove(key);
    }

    /**
     * 获取cache对象
     *
     * @param cacheName
     * @return
     */
    public Cache get(String cacheName) {
        return cacheManager.getCache(cacheName);
    }


    public interface OnCacheEmpty {
        Object getCacheData();
    }
}
