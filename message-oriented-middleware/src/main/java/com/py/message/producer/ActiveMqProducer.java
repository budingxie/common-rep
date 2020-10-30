package com.py.message.producer;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/28
 */
public class ActiveMqProducer {

    public static void main(String[] args) throws JMSException {
//        sendQueue("hello activeMq, queue一条测试数据");
        sendTopic("hello activeMq, topic一条测试数据");
    }

    public static void sendQueue(String message) throws JMSException {
        // 1.创建ConnectionFactory对象，需要指定服务端ip以及端口号。
        // 2.使用ConnectionFactory对象创建一个Connection对象。
        // 3.开启连接，调用Connection对象的start方法。
        // 4.使用Connection对象创建一个Session对象。
        //  参数1：是否开启事务（true开启），忽略参数2。
        //  参数2：当参数1为false，才有意义。消息应答模式。1、自动应答；2、手动应答；
        // 5.使用Session对象创建一个Destination对象（topic、queue），此处创建一个Queue对象。参数：队列名称
        // 6.使用Session对象创建一个Producer对象。
        // 7.创建一个Message对象，创建一个TextMessage对象。
        // 8.使用Producer对象发送消息。
        // 9.关闭资源。

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.134.100:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("py-queue");
        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage(message);
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
        System.out.println("消息发送完成");
    }

    public static void sendTopic(String message) throws JMSException {
        // 1.创建ConnectionFactory对象，需要指定服务端ip以及端口号。
        // 2.使用ConnectionFactory对象创建一个Connection对象。
        // 3.开启连接，调用Connection对象的start方法。
        // 4.使用Connection对象创建Session对象。
        //  参数1：是否开启事务（true开启），忽略参数2。
        //  参数2：当参数1为false，才有意义。消息应答模式。1、自动应答；2、手动应答；
        // 5.使用Session对象创建一个Destination对象（topic、queue），此处创建一个Topic；参数：topic名称
        // 6.使用Session对象创建一个Producer对象。
        // 7.创建一个Message对象
        // 8.使用Producer对象发送消息
        // 9.关闭资源

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.134.100:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("py-topic");
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage(message);
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
        System.out.println("消息发送完成");
    }
}
