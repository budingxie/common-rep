package com.py.message.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/10/28
 */
public class ActiveMqConsumer {

    public static void main(String[] args) throws JMSException, IOException {
//        consumerQueue();
        consumerTopic();
    }

    public static void consumerQueue() throws JMSException, IOException {
        // 1.创建一个ConnectionFactory对象
        // 2.通过ConnectionFactory对象获取一个Connection
        // 3.开启连接，调用Connection的start方法。
        // 4.使用Connection对象创建一个Session对象。
        // 5.使用Session对象创建一个Destination对象，和发送端保持一致queue，并且队列的名称一致。
        // 6.使用Session对象创建一个Consumer对象。
        // 7.接收消息
        // 8.打印消息。
        // 9.关闭资源

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.134.100:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("py-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("consumer-queue:" + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        //等待键盘输入，类似于阻塞。
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

    public static void consumerTopic() throws JMSException, IOException {
        // 1.创建一个ConnectionFactory对象
        // 2.通过ConnectionFactory对象获取一个Connection
        // 3.开启连接，调用Connection的start方法。
        // 4.使用Connection对象创建一个Session对象。
        // 5.使用Session对象创建一个Destination对象，和发送端保持一致topic，并且topic的名称一致。
        // 6.使用Session对象创建一个Consumer对象。
        // 7.接收消息
        // 8.打印消息。
        // 9.关闭资源

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.134.100:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("py-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("consumer-topic:" + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        //等待键盘输入，类似于阻塞。
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}