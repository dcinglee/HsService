package com.hutservice.application.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 微信accesstoken缓存实现类，每次需要token时从缓存中获取
 *
 * @author lid
 * @date 2017.2.24
 */
public class AccessTokenCache implements Cache {
    private static CacheManager cacheManager = CacheManager.getInstance();
    private net.sf.ehcache.Cache cache;

    public AccessTokenCache() {
        // accessToken缓存最长7200秒，系统设置为7000秒，每超过7000秒重新获取accesstoken
        cache = new net.sf.ehcache.Cache("AccessTokenCache", 1000, false, false, 7000, 0);
        AccessTokenCache.cacheManager.addCache(cache);
    }

    @Override
    public void put(Object key, Object value) {
        Element element = new Element(key, value);
        cache.put(element);
    }

    @SuppressWarnings("unchecked")
	@Override
    public <E> E get(Object key) {
        Element element = cache.get(key);
        return element == null ? null : (E) element.getObjectValue();
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public void removeAll() {
        cache.removeAll();
    }

    @Override
    public long getSize() {
        return cache.getSize();
    }
}
