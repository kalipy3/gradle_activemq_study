readme.txt

:Author: kalipy
:Email: kalipy@debian
:Date: 2020-11-25 21:43

在setDeliveryMode(DeliveryMode.PERSISTENT)情况下：(是activemq的默认方式)
    先启动生产者
    再重启activemp服务器
    在运行消费者

    结果：
        可以继续消费上次由于服务器宕机而未来得及消费的消息，因为是持久方式
