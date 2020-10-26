package com.py.dao.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/26
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DynamicDataSourceContextHolder.getDataSourceRouterKey();
        if (dataSourceName == null) {
            log.info("当前查询未指定数据源，使用默认数据源default-datasource");
        } else {
            log.info("当前数据源是：{}", dataSourceName);
        }
        return dataSourceName;
    }
}
