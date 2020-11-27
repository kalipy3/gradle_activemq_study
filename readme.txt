readme.txt

:Author: kalipy
:Email: kalipy@debian
:Date: 2020-11-25 21:43

在setDeliveryMode(DeliveryMode.NON_PERSISTENT)情况下：
    先启动生产者
    再重启activemp服务器
    在运行消费者

    结果：
        不能消费，因为是非持久方式
