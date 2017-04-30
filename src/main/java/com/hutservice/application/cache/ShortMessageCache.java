package com.hutservice.application.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 短信缓存实现类。
 *
 * @author Ewing
 * @date 2017/2/14
 */
public class ShortMessageCache implements Cache {
    private static CacheManager cacheManager = CacheManager.getInstance();
    private net.sf.ehcache.Cache cache;

    public ShortMessageCache() {
        // 短信有效期为600秒，即十分钟。
        cache = new net.sf.ehcache.Cache("ShortMessageCache", 1000000, false, false, 600, 0);
        ShortMessageCache.cacheManager.addCache(cache);
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
