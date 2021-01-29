package com.py.designpattern.singleton;

/**
 * description：饿汉式
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/29
 */
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}