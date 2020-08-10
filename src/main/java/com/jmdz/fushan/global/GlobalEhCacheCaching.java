package com.jmdz.fushan.global;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * GlobalEhCacheCaching
 *
 * @author LiCongLu
 * @date 2020-07-08 10:55
 */
@Configuration
@EnableCaching
public class GlobalEhCacheCaching {

    /**
     * applicationCache
     *
     * @param cacheManager
     * @return
     */
    @Bean(name = "applicationCache")
    public Cache getApplicationCache(CacheManager cacheManager) {
        return cacheManager.getCache("application");
    }

    /**
     * sessionCache
     *
     * @param cacheManager
     * @return
     */
    @Bean(name = "sessionCache")
    public Cache getSessionCache(CacheManager cacheManager) {
        return cacheManager.getCache("session");
    }

    /**
     * ehcache 主要的管理器
     *
     * @param bean
     * @return
     */
    @Bean(name = "cacheManager")
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
        return new EhCacheCacheManager(bean.getObject());
    }

    /**
     * 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     *
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

}
