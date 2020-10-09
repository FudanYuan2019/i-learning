### 8. Redis集群
Redis单机部署在测试环境是可以的，但是如果在生产环境也是单机的话，就会有单点故障，因此需要引入集群，保证redis集群的高可用。Redis集群部署有三种方案：
- 主从复制模式
- 哨兵模式
- 集群模式

#### 8.1 主从复制模式
##### 8.1.1 主从复制结构
![Redis主从复制结构](https://upload-images.jianshu.io/upload_images/17795057-8a1a0371da5891e4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
主从复制模式中包含一个主数据库实例（master）与一个或多个从数据库实例（slave），如上图。客户端可对主数据库进行读写操作，对从数据库进行读操作，主数据库写入的数据会实时自动同步给从数据库。

##### 8.1.2 主从复制工作机制
当slave启动后，主动向master发送`PSYNC`命令。master接收到`PSYNC`命令后在后台保存快照（RDB持久化）和缓存保存快照这段时间的命令，然后将保存的快照文件和缓存的命令发送给slave。slave接收到快照文件和命令后加载快照文件和缓存的执行命令。复制初始化后，master每次接收到的写命令都会同步发送给slave，保证主从数据一致性。
 ![主从复制工作机制](https://upload-images.jianshu.io/upload_images/17795057-c4206e7f291ed38a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 8.1.3 搭建过程
1. 我们在[Redis学习笔记系列(一)——Redis简介及安装](https://www.jianshu.com/p/bd386de19a3b)一节中，已经介绍过单机部署的步骤了，现在我们需要再部署两台redis服务器，其中一台redis服务器做Master节点，另外两个节点作为Slave节点。每个节点的部署方法可参照前面那篇文章。

2. 修改配置文件。
修改Master节点的配置文件`redis.conf`，允许外部连接，并且开启密码验证。
```
# 是否开启保护模式（默认开启）
# 要是配置里没有指定bind和密码。
# 开启该参数后，redis只会本地进行访问，拒绝外部访问。
# 要是开启了密码和bind，可以开启。否则最好关闭，设置为no。
protected-mode no

# 指定redis只接收指定IP地址的请求
# 如需处理所有请求（远程访问） bind 0.0.0.0
bind  0.0.0.0

# 设置密码
requirepass 123456
```

修改Slave节点的配置文件，如下：
```
# 复制选项，slave复制对应的master
slaveof master 6379

# 如果master设置了requirepass，那么slave要连上master，需要有master的密码才行。
# masterauth就是用来配置master的密码，这样可以在连上master后进行认证
masterauth 123456

# 当slave同master失去连接或者复制正在进行，slave的运行方式
# 1.如果slave-serve-stale-data设置为yes(默认设置)，slave会继续响应客户端的请求。
# 2.如果slave-serve-stale-data设置为no，除去INFO和SLAVOF命令之外的任何请求都会返回一个错误”SYNC with master in progress”。
slave-serve-stale-data no
```

3. 分别启动Master节点和Slave节点，在Master节点和Slave节点分别查看节点状态。
```
master: redis-cli
127.0.0.1:6379>  auth 123456
OK

# Master节点
127.0.0.1:6379> info replication
# Replication
role:master
connected_slaves:2
slave0:ip=192.168.6.4,port=6379,state=online,offset=179,lag=0
slave1:ip=192.168.6.5,port=6379,state=online,offset=179,lag=1
master_replid:f0202b49b5d88ad57ed0bde33ffbc3b260924330
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:179
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:179

# Slave节点
127.0.0.1:6379> info replication
# Replication
role:slave
master_host:master
master_port:6379
master_link_status:up
master_last_io_seconds_ago:5
master_sync_in_progress:0
slave_repl_offset:3287
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:f0202b49b5d88ad57ed0bde33ffbc3b260924330
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:3287
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:3287
```

4. 测试功能
```
# Master节点，可读可写
127.0.0.1:6379> SET k1 v1
OK
127.0.0.1:6379> GET k1
"v1"

# Slave节点，只可读
127.0.0.1:6379> GET k1
"v1"
127.0.0.1:6379> SET k1 v2
(error) READONLY You can't write against a read only replica.
```

##### 8.1.4 优缺点分析
- 优点：
1. Master能自动将数据同步到Slave，可以进行读写分离，分担Master的读压力
2. Master与Slave之间的同步是以非阻塞的方式进行的，同步期间，客户端仍然可以提交查询或更新请求。

- 缺点：
1. 不具备自动容错与恢复功能，Master或Slave的宕机都可能导致客户端请求失败，需要等待机器重启或手动切换客户端IP才能恢复。
2. Master宕机，如果宕机前数据没有同步完，则切换IP后会存在数据不一致的问题。难以支持在线扩容，Redis的容量受限于单机配置。


#### 上一篇：[Redis事务](09-Redis事务.md)
#### 下一篇：[Redis集群之哨兵模式](11-Redis集群之哨兵模式.md)