package com.py;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * description：EnableDubbo开启rpc服务、EnableCaching开启缓存
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/27
 */
@SpringBootApplication
@EnableDubbo
@EnableCaching
public class CacheAppBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(CacheAppBootstrap.class, args);
    }
}
