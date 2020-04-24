package com.wangjiayu.springboot.demo.guaua;

import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class cache {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        cache();
        //cache1();
        //loadingCache();
    }

    private static void loadingCache() throws ExecutionException {
        LoadingCache<String, Object> loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Object>() {
            @Override
            public Object load(String key) throws Exception {
                System.out.println("加载缓存");
                return "123";
            }
        });
        System.out.println(loadingCache.get("key"));
        System.out.println(loadingCache.get("key"));
    }


    private static void cache1() throws InterruptedException, ExecutionException {
        CacheLoader<String, Object> cacheLoader = new CacheLoader<String, Object>() {
            @Override
            public Object load(String key) throws Exception {
                System.out.println("加载缓存");
                return null;
            }
        };

        RemovalListener<String, Object> removalListener = new RemovalListener<String, Object>() {
            @Override
            public void onRemoval(RemovalNotification<String, Object> notification) {
                System.out.println("缓存到期了");
                System.out.println(notification);
            }
        };

        LoadingCache<String, Object> loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .removalListener(removalListener)
                .build(cacheLoader);

        loadingCache.put("key", "value");
        Thread.sleep(6000);
        System.out.println(loadingCache.get("key"));
    }


    private static void cache() throws ExecutionException {
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .concurrencyLevel(2) // 设置并发级别为10
                .recordStats() // 开启缓存统计
                .build();

        // 放入缓存
        cache.put("key", "value");
        cache.put("key2", "value");
        cache.put("key3", "value");
        // 获取缓存
        System.out.println(cache.asMap());
        System.out.println(cache.size());

        //cache.invalidateAll();

        System.out.println(cache.getIfPresent("key"));
        Object key = cache.get("key", () -> "1231312321");
        System.out.println(key);

        System.out.println(cache.stats());
    }

}
