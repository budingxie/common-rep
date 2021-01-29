package com.py.designpattern.builder;

/**
 * description
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/29
 */
public class ParlourDecorator {

    public static void main(String[] args) {
        Decorator decorator = new ConcreteDecorator1();
        ProjectManager projectManager = new ProjectManager(decorator);
        Parlour parlour = projectManager.decorate();
        parlour.show();
    }

}

/**
 * 产品：客厅
 */
class Parlour {

    private String wall;

    private String TV;

    private String sofa;

    public void setWall(String wall) {
        this.wall = wall;
    }

    public void setTV(String TV) {
        this.TV = TV;
    }

    public void setSofa(String sofa) {
        this.sofa = sofa;
    }

    public void show() {
        System.out.println(wall);
        System.out.println(TV);
        System.out.println(sofa);
    }
}

/**
 * 抽象建造者：装修工人
 */
abstract class Decorator {

    /**
     * 创建产品对象
     */
    protected Parlour product = new Parlour();

    public abstract void buildWall();

    public abstract void buildTV();

    public abstract void buildSofa();

    /**
     * 返回产品对象
     *
     * @return
     */
    public Parlour getResult() {
        return product;
    }
}

/**
 * 具体建造者：具体装修工人1
 */
class ConcreteDecorator1 extends Decorator {

    @Override
    public void buildWall() {
        product.setWall("w1");
    }

    @Override
    public void buildTV() {
        product.setTV("TV1");
    }

    @Override
    public void buildSofa() {
        product.setSofa("sf1");
    }
}

/**
 * 具体建造者：具体装修工人2
 */
class ConcreteDecorator2 extends Decorator {

    @Override
    public void buildWall() {
        product.setWall("w2");
    }

    @Override
    public void buildTV() {
        product.setTV("TV2");
    }

    @Override
    public void buildSofa() {
        product.setSofa("sf2");
    }
}

/**
 * 指挥者：项目经理
 */
class ProjectManager {

    private Decorator builder;

    public ProjectManager(Decorator builder) {
        this.builder = builder;
    }

    /**
     * 产品构建与组装方法
     *
     * @return
     */
    public Parlour decorate() {
        builder.buildWall();
        builder.buildTV();
        builder.buildSofa();
        return builder.getResult();
    }
}

