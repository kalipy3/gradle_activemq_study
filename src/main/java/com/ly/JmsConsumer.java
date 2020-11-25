package com.ly;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/*
 * JmsConsumer.java
 * Copyright (C) 2020 2020-11-25 22:33 kalipy <kalipy@debian>
 *
 * Distributed under terms of the MIT license.
 */

public class JmsConsumer
{
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer messageConsumer = session.createConsumer(queue);
        //同步阻塞方式
        //订阅者或接受者调用MessageConsumer的receive()来接收消息，receive在接收到消息之前或超时之前将一直阻塞
        while (true) {
            //TextMessage textMessage = (TextMessage)messageConsumer.receive();//阻塞式
            TextMessage textMessage = (TextMessage)messageConsumer.receive(4000L);//阻塞式
            if (null != textMessage) {
                System.out.println("消费者接收到消息：" + textMessage.getText());
            } else {
                break;
            }
        }
        messageConsumer.close();
        session.close();
        connection.close();
    }

}

