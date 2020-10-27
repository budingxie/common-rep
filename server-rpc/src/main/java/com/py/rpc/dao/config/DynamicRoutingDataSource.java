package com.py.rpc.dao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * description：数据源设置生效
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/26
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private static final Logger log = LoggerFactory.getLogger(DynamicRoutingDataSource.class);

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
