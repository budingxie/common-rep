package com.py.designpattern.adapter;

/**
 * description
 * 1.目标
 * 2.适配者
 * 3.适配器
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/29
 */
public class AdapterTest {

    public static void main(String[] args) {
        System.out.println("类适配器模式测试：");
        Target target = new ClassAdapter();
        target.request();

        System.out.println("对象适配器模式测试：");
        Adapter adapter = new Adapter();
        Target target1 = new ObjectAdapter(adapter);
        target1.request();
    }
}

/**
 * 目标接口
 */
interface Target {
    void request();
}

/**
 * 适配者
 */
class Adapter {
    public void specificRequest() {
        System.out.println("适配者中的业务代码被调用！");
    }
}

/**
 * 类适配器类
 */
class ClassAdapter extends Adapter implements Target {

    @Override
    public void request() {
        specificRequest();
    }
}

class ObjectAdapter implements Target {

    private Adapter adapter;

    public ObjectAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void request() {
        adapter.specificRequest();
    }
}