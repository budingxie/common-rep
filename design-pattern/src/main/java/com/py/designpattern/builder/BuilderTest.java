package com.py.designpattern.builder;

/**
 * description
 * 结构
 * 1.产品角色
 * 2.抽象建造者
 * 3.具体建造者
 * 4.指挥者
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/29
 */
public class BuilderTest {

    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        product.show();
    }
}

/**
 * 产品
 */
class Product {

    private String partA;

    private String partB;

    private String partC;

    public void show() {
        System.out.println(partA);
        System.out.println(partB);
        System.out.println(partC);
    }

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }
}

/**
 * 抽象建造者
 */
abstract class Builder {

    protected Product product = new Product();

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    /**
     * 返回产品对象
     *
     * @return
     */
    public Product getResult() {
        return product;
    }
}

/**
 * 具体建造者
 */
class ConcreteBuilder extends Builder {

    @Override
    public void buildPartA() {
        product.setPartA("建造 PartA");
    }

    @Override
    public void buildPartB() {
        product.setPartB("建造 PartB");
    }

    @Override
    public void buildPartC() {
        product.setPartC("建造 PartC");
    }
}

/**
 * 指挥者
 */
class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 产品构建与组装方法
     *
     * @return
     */
    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}
