readme.txt

:Author: kalipy
:Email: kalipy@debian
:Date: 2020-11-25 21:43

1.如果不指定activemq的网络监听端口，那么这些端口都将使用BIO的网络IO模型,为了提高单节点的网络吞吐性能，我们可以使用NIO模型

2.怎么使用NIO模型？
    修改配置文件activemq.xml,<transportConnectors>标签内添加:
        <transportConnector name="nio" uri="nio://0.0.0.0:61818?trace=true" />

    生产和消费两端协议代码修改
        public static final String ACTIVEMQ_URL = "nio://127.0.0.1:61618"

    运行验证
        重启activemq服务器，127.0.0.1:8161/admin/connections.jsp页面出现Connector nio
        消费者与生产者也正常运行
