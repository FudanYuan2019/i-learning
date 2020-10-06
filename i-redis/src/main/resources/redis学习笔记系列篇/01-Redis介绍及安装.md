### 1. Redis介绍
Redis是完全**开源**的，遵守 BSD 协议，是一个高性能的 **key-value** 数据库。

Redis与其他key-value缓存产品有以下三个特点：
* Redis支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用。
* Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储。
* Redis支持数据的备份，即master-slave模式的数据备份。

### 2. Redis应用场景
* 热点数据（数据查询、短连接、新闻内容、商品内容等等）**缓存**。
* 分布式集群架构中的共享session。
* 聊天室的在线好友列表（bitmap）
* 任务队列（秒杀、抢购、12306等等）。
* 应用排行榜。
* 网站访问统计。
* 数据过期处理（可以精确到毫秒）。

### 3. Redis安装
3.1. 通过[Redis官网](https://redis.io/)下载压缩包或者通过命令行下载：
```
wget http://download.redis.io/releases/redis-6.0.8.tar.gz
```
3.2. 解压并且编译：
```
tar xzf redis-6.0.8.tar.gz
cd redis-6.0.8
make install
```
3.3. 编译完成后，会生成`bin`目录，
```
cd ./bin
ls
```
目录下含有如下文件：
```
redis-benchmark  # redis性能测试工具
redis-check-aof  # AOF文件修复工具
redis-check-rdb  # RDB文件修复工具
redis-cli  # redis命令行客户端
redis.conf  # redis配置文件
redis-sentinal  # redis集群管理工具
redis-server  # redis服务进程
```

3.4. 将配置文件`redis.conf`复制到`bin`目录下。
```
cp ../redis.conf ./
```

3.5. 前端启动redis-server
```
./redis-server
```
如图：
![服务端启动成功](https://upload-images.jianshu.io/upload_images/17795057-5e0a69e97bf42053.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
> `./redis-server`使用的是默认配置。如果需要加载配置文件启动，可以使用命令：`./redis-server ./redis.conf`。

3.6. 后端启动redis-server
第5步中是以前端方式启动的redis，这种方式启动的缺点是ssh命令窗口关闭则redis-server程序结束，不推荐使用此方法，推荐使用后端启动方式。
修改配置文件redis.conf，将`daemonize no` 修改为 `daemonize yes`，然后加载配置文件进行启动：
```
./redis-server ./redis.conf
```
使用`ps`命令验证下：
```
ps aux | grep redis
# hadoop   17537  0.0  0.3  69708  3644 ?        Ssl  15:25   0:00 ./redis-server 127.0.0.1:6379
# hadoop   17567  0.0  0.0  21536   992 pts/0    S+   15:25   0:00 grep --color=auto redis
```

3.7. 启动redis-cli，连接redis服务
```
./redis-cli
```
![客户端启动成功](https://upload-images.jianshu.io/upload_images/17795057-e318a34e3fdf284e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3.8. 关闭redis-server。在redis-cli内使用命令：
```
shutdown
```

3.9. 退出redis-cli，使用命令 `exit` 或者 `quit`。

下一篇：[Redis配置文件详解](./src/main/resources/redis学习笔记系列篇/02-Redis配置文件详解.md)