package com.ly;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import org.apache.activemq.ActiveMQConnectionFactory;

/*
 * JmsConsumer.java
 * Copyright (C) 2020 2020-11-25 22:33 kalipy <kalipy@debian>
 *
 * Distributed under terms of the MIT license.
 */

public class JmsConsumer_Topic_Persistent
{
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "topic-jdbc-persistent";

    public static void main(String[] args) throws JMSException, IOException, Exception  {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        //需要设置消费者id,用来识别消费者
        connection.setClientID("atkalipy01");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic= session.createTopic(TOPIC_NAME);
        //要创建TopicSubscriber来订阅
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "mq-jdbc");
        //一定要先运行一次，等于向mq服务器注册这个消费者，然后再运行生产者发送消息，这样，无论消费者是否在线，都会接收到，不在线的话，下次连接的时候，会把没有收过的消息都接收下来
        connection.start();
        //接收消息
        Message message = topicSubscriber.receive();
        while (null != message) {
            TextMessage textMessage = (TextMessage)message;
            System.out.println("消费者接收到消息："+textMessage.getText());
            message = topicSubscriber.receive(5000);
        }

        Thread.sleep(10*1000);//用sleep代替read()
        session.close();
        connection.close();
    }
}

