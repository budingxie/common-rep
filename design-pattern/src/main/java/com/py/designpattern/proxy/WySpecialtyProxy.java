package com.py.designpattern.proxy;

/**
 * description
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/29
 */
public class WySpecialtyProxy {

    public static void main(String[] args) {
        SgProxy proxy = new SgProxy();
        proxy.display();
    }
}

/**
 * 抽象主题：特产
 */
interface Specialty {
    void display();
}

/**
 * 真实主题：婺源特产
 */
class WySpecialty implements Specialty {

    @Override
    public void display() {
        System.out.println("婺源特产");
    }
}

/**
 * 代理：韶关代理
 */
class SgProxy implements Specialty {

    private WySpecialty realSubject = new WySpecialty();

    @Override
    public void display() {
        preRequest();
        realSubject.display();
        postRequest();
    }

    public void preRequest() {
        System.out.println("韶关代理婺源特产开始。");
    }

    public void postRequest() {
        System.out.println("韶关代理婺源特产结束。");
    }
}