package com.hutservice.application.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存实现类。
 *
 * @author Ewing
 * @date 2017/2/14
 */
public class SessionCache implements Cache {
    private static CacheManager cacheManager = CacheManager.getInstance();
    private net.sf.ehcache.Cache cache;

    public SessionCache() {
        // 缓存最长生存时间为2592000秒（30天），最长空闲时间604800秒（7天）
        cache = new net.sf.ehcache.Cache("SessionCache", 1000000, false, false, 2592000, 604800);
        SessionCache.cacheManager.addCache(cache);
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
