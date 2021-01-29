package com.py.designpattern.factory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;

/**
 * description
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/29
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        try {
            AbstractFactory af = (AbstractFactory) ReadXML1.getObject();
            Product a = af.newProduct();
            a.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

interface Product {
    void show();
}

/**
 * 具体产品1
 */
class ConcreteProduct1 implements Product {

    @Override
    public void show() {
        System.out.println("具体产品1显示。。。");
    }
}

/**
 * 具体产品2
 */
class ConcreteProduct2 implements Product {

    @Override
    public void show() {
        System.out.println("具体产品2显示。。。");
    }
}

/**
 * 抽象工厂：提供了产品生成的方法
 */
interface AbstractFactory {
    Product newProduct();
}

/**
 * 具体工厂1：实现了厂品的生成方法
 */
class ConcreteFactory1 implements AbstractFactory {

    @Override
    public Product newProduct() {
        System.out.println("具体工厂1生成-->具体产品1...");
        return new ConcreteProduct1();
    }
}

/**
 * 具体工厂2：实现了厂品的生成方法
 */
class ConcreteFactory2 implements AbstractFactory {

    @Override
    public Product newProduct() {
        System.out.println("具体工厂2生成-->具体产品1...");
        return new ConcreteProduct2();
    }
}

class ReadXML1 {

    /**
     * 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
     *
     * @return
     */
    public static Object getObject() {
        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            URL resource = ReadXML1.class.getClassLoader().getResource("FactoryMethod/config1.xml");
            String path = resource.getPath();
            Document doc = builder.parse(new File(path));
            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String cName = classNode.getNodeValue();
            System.out.println("新类名："+cName);
            // 通过类名生成实例对象并将其返回
            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
