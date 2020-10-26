package com.py.dao.config;

import java.lang.annotation.*;

/**
 * description：切换数据注解 可以用于类或者方法级别 方法级别优先 > 类级别
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/26
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    /**
     * 该值即key值
     */
    String value() default "default-source";
}
