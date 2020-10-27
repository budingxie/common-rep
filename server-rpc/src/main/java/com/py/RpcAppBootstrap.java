package com.py;

import com.py.rpc.dao.config.DynamicDataSourceRegister;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * description：Import注册动态数据源、MapperScan：扫描Mapper接口、EnableDubbo开启rpc服务
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/27
 */
@SpringBootApplication
@Import(DynamicDataSourceRegister.class)
@MapperScan("com.py.rpc.dao")
@EnableDubbo
public class RpcAppBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(RpcAppBootstrap.class, args);
    }
}
