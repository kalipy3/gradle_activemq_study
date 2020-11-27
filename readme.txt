readme.txt

:Author: kalipy
:Email: kalipy@debian
:Date: 2020-11-25 21:43

JDBC消息存储：
    1.安装mysql

    2.添加mysql数据库的驱动包到mq的lib文件夹
        cp /home/kalipy/.gradle/caches/modules-2/files-2.1/mysql/mysql-connector-java/8.0.12/8e201602cc1ddd145c4c74e67d4002d3d4b1796/mysql-connector-java-8.0.12.jar /home/kalipy/下载/apache-activemq-5.16.0/apache-activemq-5.16.0/lib/.

    3.jdbcPersistenceAdapter配置，activemq.xml中，注释kahaDB，加入jdbc的
        <!--
        <persistenceAdapter>
            <kahaDB directory="${activemq.data}/kahadb"/>
            </persistenceAdapter>
        -->
        <persistenceAdapter>
            <jdbcPersistenceAdapter dataSource="#mysql-ds"/>
        </persistenceAdapter>

        然后配置mysql的驱动位置：
        <bean id="mysql-ds" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
           <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
           <property name="url" value="jdbc:mysql://localhost/activemq"/>
           <property name="username" value="activemq"/>
           <property name="password" value="activemq"/>
           <property name="poolPreparedStatements" value="true"/>
         </bean>

    4.数据库连接池配置

    5.建库建表
        库一定要手动建：
            create database activemq
        表mq会自动建

    6.代码运行验证
        一定要开启setDeliveryMode()持久化

    7.查看数据库情况


在点对点模型(queue)中
    当DeliveryMode设置为NON_PERSISTENCE时，消息被保存在内存中
    当DeliveryMode设置为PERSISTENCE时，消息被保存在brokder的相应文件里或DB中

    消息一旦被消费，就会从broker或DB中删除

