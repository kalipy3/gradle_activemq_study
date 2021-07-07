    readme.md
    
    :Author: kalipy
    :Email: kalipy@debian
    :Date: 2021-07-07 20:16

### activemq消息持久化机制之JDBC With Journal

是什么

![Image](./img/image_2021-07-07-20-19-51.png)

配置

![Image](./img/image_2021-07-07-20-24-31.png)

重启activemq

    ./activemq restart

测试

![Image](./img/image_2021-07-07-20-32-29.png)

生产者生产了消息，而消费者没有消费时，数据库要过一段时间才会从缓存(缓存日志)同步到mysql磁盘。(数据被消费后，数据的删除也是先删除缓存里的，然后再过一段时间，才会删除mysql本地的)

### 持久化机制小总结

![Image](./img/image_2021-07-07-20-41-21.png)

![Image](./img/image_2021-07-07-20-42-18.png)

### zookeeper和replicated levelDB集群原理

![Image](./img/image_2021-07-07-20-45-12.png)

是什么

![Image](./img/image_2021-07-07-20-46-36.png)

三种集群方式对比

![Image](./img/image_2021-07-07-20-49-25.png)

本次案例采用zookeeper+replicated LevelDB Store

![Image](./img/image_2021-07-07-20-51-18.png)

![Image](./img/image_2021-07-07-20-52-07.png)

![Image](./img/image_2021-07-07-20-53-21.png)

![Image](./img/image_2021-07-07-20-54-27.png)

![Image](./img/image_2021-07-07-20-56-58.png)

### 部署之前

![Image](./img/image_2021-07-07-21-01-24.png)

![Image](./img/image_2021-07-07-21-03-19.png)

![Image](./img/image_2021-07-07-21-04-22.png)

### 集群部署上

![Image](./img/image_2021-07-07-21-07-14.png)

![Image](./img/image_2021-07-07-21-08-29.png)

![Image](./img/image_2021-07-07-21-08-48.png)

![Image](./img/image_2021-07-07-21-09-22.png)

![Image](./img/image_2021-07-07-21-10-21.png)

![Image](./img/image_2021-07-07-21-11-14.png)

![Image](./img/image_2021-07-07-21-12-20.png)

![Image](./img/image_2021-07-07-21-13-03.png)

![Image](./img/image_2021-07-07-21-13-32.png)

![Image](./img/image_2021-07-07-21-14-50.png)

### 集群部署上

![Image](./img/image_2021-07-07-21-16-11.png)

注意: 在最开始我们就说过,采用LevelDB:

![Image](./img/image_2021-07-07-21-17-02.png)

![Image](./img/image_2021-07-07-21-18-25.png)

注释掉默认的kahadb:

![Image](./img/image_2021-07-07-21-21-20.png)

把这个一粘贴:

![Image](./img/image_2021-07-07-21-23-49.png)

再配置node2:

![Image](./img/image_2021-07-07-21-24-50.png)

然后配置node3:

![Image](./img/image_2021-07-07-21-25-19.png)

![Image](./img/image_2021-07-07-21-26-34.png)

![Image](./img/image_2021-07-07-21-27-09.png)

![Image](./img/image_2021-07-07-21-27-27.png)

![Image](./img/image_2021-07-07-21-29-17.png)

![Image](./img/image_2021-07-07-21-29-59.png)

![Image](./img/image_2021-07-07-21-30-48.png)

![Image](./img/image_2021-07-07-21-32-57.png)

![Image](./img/image_2021-07-07-21-32-22.png)

![Image](./img/image_2021-07-07-21-33-39.png)

![Image](./img/image_2021-07-07-21-34-29.png)

![Image](./img/image_2021-07-07-21-35-38.png)

![Image](./img/image_2021-07-07-21-37-25.png)

### Replicated LevelDB集群故障迁移和验证

![Image](./img/image_2021-07-07-21-42-24.png)

broker访问测试:

![Image](./img/image_2021-07-07-21-51-25.png)

![Image](./img/image_2021-07-07-21-52-38.png)

![Image](./img/image_2021-07-07-21-53-28.png)

![Image](./img/image_2021-07-07-21-54-44.png)

![Image](./img/image_2021-07-07-21-55-43.png)

改为:

![Image](./img/image_2021-07-07-21-55-59.png)

Consumer的也一样:

![Image](./img/image_2021-07-07-21-56-34.png)

#### 正常情况下

先运行生产者生产消息:

![Image](./img/image_2021-07-07-21-58-00.png)

查看结果:

![Image](./img/image_2021-07-07-21-58-35.png)

然后运行消费者消费消息:

![Image](./img/image_2021-07-07-22-00-05.png)

![Image](./img/image_2021-07-07-22-00-43.png)

#### 现在我们

![Image](./img/image_2021-07-07-22-01-47.png)

我们现在干掉activemq_master:

![Image](./img/image_2021-07-07-22-04-16.png)

可以看到现在8161就无法访问了:

![Image](./img/image_2021-07-07-22-05-07.png)

但是选举出了新的activemq_master:

![Image](./img/image_2021-07-07-22-06-16.png)



