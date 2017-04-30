package com.hutservice.application.cache;

/**
 * 缓存接口。
 *
 * @author Ewing
 * @date 2017/2/14
 */
public interface Cache {

    /**
     * 添加对象进缓存。
     */
    void put(Object key, Object value);

    /**
     * 从缓存中取出对象。
     */
    <E> E get(Object key);

    /**
     * 删除缓存中的元素。
     */
    void remove(String key);

    /**
     * 删除缓存中的所有元素。
     */
    void removeAll();

    /**
     * 获取总元素个数。
     */
    long getSize();
}
