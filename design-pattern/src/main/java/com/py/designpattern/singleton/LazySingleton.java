package com.py.designpattern.singleton;

/**
 * description：懒汉式
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/29
 */
public class LazySingleton {

    private static volatile LazySingleton instance = null;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
