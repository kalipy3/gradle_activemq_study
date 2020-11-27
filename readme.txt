readme.txt

:Author: kalipy
:Email: kalipy@debian
:Date: 2020-11-25 21:43

1.activemq.xml恢复到以前没nio时候的

2.MQ的高可用：
    1.事务
    2.持久 --->activemq自带
    3.签收

    4.可持久化 --->另一台机器上的mysql等数据库

可持久化：
    什么是持久化？
        mq服务器down机了，消息不会丢失的机制

    有哪些？
        有JDBC AMQ KahaDB(mq默认) LevelDB等

    原理：
        在发送消息的时候，mq服务器首先将消息存储到本地数据文件、内存数据库或者远程数据库等，再试图将消息发送给接受者，成功则将消息从存储中删除，失败则继续尝试发送
        mq服务器启动后首先检查指定的存储位置，如果有未发送成功的消息，则需要把消息发送出去

