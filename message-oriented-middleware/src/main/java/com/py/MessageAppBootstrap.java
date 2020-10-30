package com.py;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/28
 */
@SpringBootApplication
@EnableJms
public class MessageAppBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(MessageAppBootstrap.class, args);
    }
}
