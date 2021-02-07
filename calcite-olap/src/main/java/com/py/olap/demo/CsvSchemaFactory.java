package com.py.olap.demo;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

import java.util.Map;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2021/2/7
 */
public class CsvSchemaFactory implements SchemaFactory {

    /**
     * @param parentSchema 他的父节点，一般为root
     * @param name         数据库的名字，它在model中的定义
     * @param operand      也是在mode中定义的，是map类型，用于传入自定义参数
     * @return
     */
    @Override
    public Schema create(SchemaPlus parentSchema, String name, Map<String, Object> operand) {
        return new CsvSchema(String.valueOf(operand.get("dataFile")));
    }
}
