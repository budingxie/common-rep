package com.py.olap.demo;

import com.google.common.collect.Lists;
import org.apache.calcite.DataContext;
import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.linq4j.AbstractEnumerable;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.schema.ScannableTable;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.util.Pair;
import org.apache.calcite.util.Source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2021/2/7
 */
public class CsvTable extends AbstractTable implements ScannableTable {

    private Source source;

    public CsvTable(Source source) {
        this.source = source;
    }

    /**
     * 获取字段类型
     *
     * @param relDataTypeFactory
     * @return
     */
    @Override
    public RelDataType getRowType(RelDataTypeFactory relDataTypeFactory) {
        JavaTypeFactory typeFactory = (JavaTypeFactory) relDataTypeFactory;
        LinkedList<String> names = Lists.newLinkedList();
        LinkedList<RelDataType> types = Lists.newLinkedList();
        try (BufferedReader reader = new BufferedReader(new FileReader(source.file()))) {
            String line = reader.readLine();
            List<String> lines = Lists.newArrayList(line.split(","));
            lines.forEach(column -> {
                String name = column.split(":")[0];
                String type = column.split(":")[1];
                names.add(name);
                types.add(typeFactory.createSqlType(SqlTypeName.get(type)));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Pair<String, RelDataType>> zip = Pair.zip(names, types);
        return typeFactory.createStructType(zip);
    }

    @Override
    public Enumerable<Object[]> scan(DataContext dataContext) {
        return new AbstractEnumerable<Object[]>() {
            @Override
            public Enumerator<Object[]> enumerator() {
                return new CsvEnumerator<>(source);
            }
        };
    }

}
