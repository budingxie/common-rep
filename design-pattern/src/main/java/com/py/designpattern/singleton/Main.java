package com.py.designpattern.singleton;

/**
 * description：
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/29
 */
public class Main {

    public static void main(String[] args) {
        testLazyPresident();
    }

    public static void testLazyPresident() {
        President zt1 = President.getInstance();
        zt1.getName();
        President zt2 = President.getInstance();
        zt2.getName();
        if (zt1 == zt2) {
            System.out.println("他们是同一个人！");
        } else {
            System.out.println("他们不是同一个人！");
        }
    }
}

/**
 * 懒汉式的应用，模拟产生美国总统对象
 */
class President {

    private static volatile President instance = null;

    private President() {
        System.out.println("产生一个总统");
    }

    public static synchronized President getInstance() {
        if (instance == null) {
            instance = new President();
        } else {
            System.out.println("已经有一个总统，不能产生新总统！");
        }
        return instance;
    }

    public void getName() {
        System.out.println("我是美国总统：拜登");
    }
}

