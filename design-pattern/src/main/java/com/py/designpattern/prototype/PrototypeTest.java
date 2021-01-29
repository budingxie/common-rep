package com.py.designpattern.prototype;

/**
 * description：原型模式
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/29
 */

public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Realizetype obj1 = new Realizetype();
        Realizetype obj2 = (Realizetype) obj1.clone();
        System.out.println("obj1==obj2?" + (obj1 == obj2));
    }
}

class Realizetype implements Cloneable {

    Realizetype() {
        System.out.println("具体原型创建成功！");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！");
        return super.clone();
    }
}

