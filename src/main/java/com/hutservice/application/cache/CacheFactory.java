package com.hutservice.application.cache;

/**
 * 缓存工厂，方便切换缓存。
 * 缓存是必须要创建的，所以立即创建单例。
 *
 * @author Ewing
 * @date 2017/2/14
 */
public class CacheFactory {

    private static Cache sessionCache = new SessionCache();

    private static Cache accessTokenCache = new AccessTokenCache();

    private static Cache shortMessageCache = new ShortMessageCache();

    public static Cache getSessionCache() {
        return sessionCache;
    }

    public static Cache getAccessTokenCache() {
        return accessTokenCache;
    }

    public static Cache getShortMessageCache() {
        return shortMessageCache;
    }
}
