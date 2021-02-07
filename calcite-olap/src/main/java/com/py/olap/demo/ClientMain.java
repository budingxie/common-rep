package com.py.olap.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2021/2/7
 */
public class ClientMain {

    /**
     * 测试的时候用字符串 defaultSchema 默认数据库 name 数据库名称 type custom factory
     * 请求接收类，该类会实例化Schema也就是数据库类，Schema会实例化Table实现类，Table会实例化数据类。
     * operand 动态参数，ScheamFactory的create方法会接收到这里的数据
     */
    public static void main(String[] args) {
        try {

            // 用文件的方式
//            URL url = ClientMain.class.getResource("model.json");
//            String str = URLDecoder.decode(url.toString(), "UTF-8");
//            Properties info = new Properties();
//            info.put("model", str.replace("file:", ""));
//            Connection connection = DriverManager.getConnection("jdbc:calcite:", info);

            // 字符串方式
            URL url = ClientMain.class.getClassLoader().getResource("model.json");
            String path = url.getPath();
            FileInputStream fis = new FileInputStream(new File(path));
            byte[] bytes = new byte[1024];
            int len = fis.read(bytes);
            String model = new String(bytes, 0, len);
            Connection connection = DriverManager.getConnection("jdbc:calcite:model=inline:" + model);
            Statement statement = connection.createStatement();
            test1(statement);

            System.out.println("=========");
            parser();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parser() {
        // https://github.com/yuqi1129/calcite-test/blob/master/README.MD
        SchemaPlus rootSchema = Frameworks.createRootSchema(true);
        FrameworkConfig config = Frameworks.newConfigBuilder()
                .parserConfig(SqlParser.configBuilder()
                        .setParserFactory(SqlParserImpl.FACTORY)
                        .setCaseSensitive(false)
                        .setQuoting(Quoting.BACK_TICK)
                        .setQuotedCasing(Casing.TO_UPPER)
                        .setUnquotedCasing(Casing.TO_UPPER)
                        .setConformance(SqlConformanceEnum.ORACLE_12)
                        .build()
                ).build();
        String sql = "select ids, name from test where id < 5 and name = 'zhang'";
        SqlParser parser = SqlParser.create(sql, config.getParserConfig());
        try {
            SqlNode sqlNode = parser.parseStmt();
            System.out.println(sqlNode.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * CSV文件读取
     *
     * @param statement
     * @throws Exception
     */
    public static void test1(Statement statement) throws Exception {
        ResultSet resultSet = statement.executeQuery("select * from test_csv.TEST01");
        System.out.println(JSON.toJSONString(getData(resultSet)));
    }


    public static List<Map<String, Object>> getData(ResultSet resultSet) throws Exception {
        List<Map<String, Object>> list = Lists.newArrayList();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnSize = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> map = Maps.newLinkedHashMap();
            for (int i = 1; i < columnSize + 1; i++) {
                map.put(metaData.getColumnLabel(i), resultSet.getObject(i));
            }
            list.add(map);
        }
        return list;
    }
}
