package com.py.web.dao.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/26
 */
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, DataSource ds) {
        String dsId = ds.value();
        if (DynamicDataSourceContextHolder.dataSourceIds.contains(dsId)) {
            DynamicDataSourceContextHolder.setDataSourceRouterKey(dsId);
            log.debug("Use DataSource :{}", dsId);
        } else {
            log.info("数据源[{}]不存在，使用默认数据源 >{}", dsId, point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceRouterKey(dsId);
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, DataSource ds) {
        log.debug("Revert DataSource : " + ds.value() + " > " + point.getSignature());
        DynamicDataSourceContextHolder.removeDataSourceRouterKey();
    }
}
