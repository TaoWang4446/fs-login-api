package com.jmdz.fushan.helper;

import org.springframework.cache.Cache;

/**
 * Cache工具类
 *
 * @author GuanKui
 * @date 2019-12-20 15:44
 */
@SuppressWarnings("unchecked")
public class CacheHelper {

    /**
     * 清除执行缓存对象的所有缓存信息
     *
     * @param caches 缓存集合
     * @return
     * @author GuanKui
     * @date 2019-12-31 14:49
     */
    public static void clearAllCacheValue(Cache... caches) {
        if (caches != null && caches.length > 0) {
            for (Cache cache : caches) {
                cache.clear();
            }
        }
    }

    /**
     * 清除缓存数据
     *
     * @param cache 缓存类
     * @param key   关键字
     * @return
     * @author GuanKui
     * @date 2019/7/11 10:38
     */
    public static void clearCacheValue(Cache cache, Object key) {
        if (cache != null && key != null) {
            cache.put(key, null);
            cache.evict(key);
        }
    }

    /**
     * 获取缓存属性值
     *
     * @param cache 缓存类
     * @param key   关键字
     * @return
     * @author GuanKui
     * @date 2019-12-20 15:47
     */
    public static <T> T getCacheValue(Cache cache, Object key) {
        return getCacheValue(cache, key, null);
    }

    /**
     * 获取缓存值
     *
     * @param cache  缓存类
     * @param key    关键字
     * @param defVal 默认值
     * @return
     * @author GuanKui
     * @date 2019-12-20 15:47
     */
    public static <T> T getCacheValue(Cache cache, Object key, T defVal) {
        try {
            if (cache != null && key != null) {
                Cache.ValueWrapper wrapper = cache.get(key);
                if (wrapper != null) {
                    Object obj = wrapper.get();
                    if (obj != null) {
                        return (T) obj;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defVal;
    }
}
