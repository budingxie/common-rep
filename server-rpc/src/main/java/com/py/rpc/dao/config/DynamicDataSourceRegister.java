package com.py.rpc.dao.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：动态数据源注册
 * 实现 ImportBeanDefinitionRegistrar 实现数据源注册
 * 实现 EnvironmentAware 用于读取application.yml配置文件
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/26
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    /**
     * 配置上下文
     */
    private Environment env;

    /**
     * 别名
     */
    private final static ConfigurationPropertyNameAliases ALIASES = new ConfigurationPropertyNameAliases();

    /**
     * 由于部分数据源配置不同，所以在此处添加别名，避免切换数据源出现某些参数无法注入的情况
     */
    static {
        ALIASES.addAliases("url", "jdbc-url");
        ALIASES.addAliases("username", "user");
    }

    /**
     * 存储我们注册的数据源
     */
    private Map<String, DataSource> customDataSources = new HashMap<>();

    /**
     * 参数绑定工具 springboot2.0新退出
     */
    private Binder binder;

    @Override
    public void setEnvironment(Environment environment) {
        log.info("开始注册数据源");
        this.env = environment;
        binder = Binder.get(env);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        // 获取所有数据源配置
        Map config, defaultDataSourceProperties;
        defaultDataSourceProperties = binder.bind("spring.datasource.default-source", Map.class).get();
        // 获取数据源类型
        String typeStr = env.getProperty("spring.datasource.default-source.type");
        // 获取数据源类型
        Class<? extends DataSource> clazz = getDataSourceType(typeStr);
        // 绑定默认数据源参数 也就是主数据源
        DataSource consumerDatasource, defaultDatasource = bind(clazz, defaultDataSourceProperties);
        DynamicDataSourceContextHolder.dataSourceIds.add("default-source");
        log.info("注册默认数据源成功");
        // 获取其他数据源配置
        List<Map> configs = binder.bind("spring.datasource.other-source", Bindable.listOf(Map.class)).get();
        // 遍历从数据源
        for (Map map : configs) {
            config = map;
            clazz = getDataSourceType((String) config.get("type"));
            defaultDataSourceProperties = config;
            // 绑定参数
            consumerDatasource = bind(clazz, defaultDataSourceProperties);
            // 获取数据源的key，以便通过该key可以定位到数据源
            String key = config.get("key").toString();
            customDataSources.put(key, consumerDatasource);
            // 数据源上下文，用于管理数据源与记录已经注册的数据源key
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
            log.info("注册数据源{}成功", key);
        }
        // bean定义类
        GenericBeanDefinition define = new GenericBeanDefinition();

        // 设置bean的类型，此处DynamicRoutingDataSource是继承AbstractRoutingDataSource的实现类
        define.setBeanClass(DynamicRoutingDataSource.class);
        // 需要注入的参数
        MutablePropertyValues mpv = define.getPropertyValues();
        // 添加默认数据源，避免key不存在的情况没有数据源可用
        mpv.add("defaultTargetDataSource", defaultDatasource);
        // 添加其他数据源
        mpv.add("targetDataSources", customDataSources);

        // 将该bean注册为datasource，不使用springboot自动生成的datasource
        beanDefinitionRegistry.registerBeanDefinition("datasource", define);
        log.info("注册数据源成功，一共注册{}个数据源", customDataSources.keySet().size() + 1);
    }


    private <T extends DataSource> T bind(Class<T> clazz, Map<?, ?> properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(source.withAliases(ALIASES));
        // 通过类型绑定参数并获得实例对象
        return binder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(clazz)).get();
    }

    private Class<? extends DataSource> getDataSourceType(String typeStr) {
        Class<? extends DataSource> type;
        try {
            if (StringUtils.hasLength(typeStr)) {
                type = (Class<? extends DataSource>) Class.forName(typeStr);
            } else {
                // 默认为hikariCP数据源，与springboot默认数据源保持一致
                type = HikariDataSource.class;
            }
        } catch (Exception e) {
            // 无法通过反射获取class对象的情况则抛出异常，该情况一般是写错了，所以此次抛出一个runtimeException
            throw new IllegalArgumentException("can not resolve class with type: " + typeStr);
        }
        return type;
    }

}
