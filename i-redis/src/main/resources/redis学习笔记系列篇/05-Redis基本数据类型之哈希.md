#### 5.3  哈希(hash)
##### 5.3.1 HSET / HSETNX 
* HSET
`HSET`命令用于设置哈希表中指定域的值，并返回新添加的域的数量。若哈希表中对应的域已存在则将覆盖其值。在Redis 4.0.0及以上版本中，`HSET`命令允许同时设置一个或多个域的值，若版本低于4.0.0可使用下文将会介绍到的HMSET命令。
```HSET key field value [field value ...]```
* 示例
```
127.0.0.1:6379> HSET hash k1 v1 k2 v2 k3 v3
(integer) 3
127.0.0.1:6379> HGET hash k1
"v1"
127.0.0.1:6379> HGET hash k2
"v2"
127.0.0.1:6379> HGET hash k3
"v3"
```

* HSETNX
`HSETNX`命令用于在哈希表中指定域不存在时设置其值，若域已存在将不执行操作。
```HSETNX key field value```
当域为一个新域且成功执行设置其值的操作后，将返回1。若在哈希表中域已存在，则不进行操作并返回0。

* 示例
```
127.0.0.1:6379> HSETNX hash k1 v4
(integer) 0
127.0.0.1:6379> HGET hash k1
"v1"
127.0.0.1:6379> HSETNX hash k4 v4
(integer) 1
127.0.0.1:6379> HGET hash k4
"v4"
```

##### 5.3.2 HGET / HGETALL 
* HGET
`HGET`命令用于获取哈希表中指定域对应的值，当哈希表不存在或哈希表中不存在指定名称的域时将返回`nil`。
```HGET key field```

* HGETALL
`HGETALL`命令用于获取哈希表中所有域的域名及其对应的值，并以列表的形式将结果返回。返回的列表将以**域名、值交替的形式**呈现，其长度固定为哈希表大小的两倍。当哈希不存在时，将视为空哈希表并返回一个空白的队列。
`HGETALL key`
* 示例
```
127.0.0.1:6379> HGETALL hash
1) "k1"
2) "v1"
3) "k2"
4) "v2"
5) "k3"
6) "v3"
7) "k4"
8) "v4"
```

##### 5.3.3 HMSET / HMGET 
* HMSET
`HMSET`命令用于设置哈希表中一个或多个域的值。执行操作时若域已存在，将覆盖原先的值；若域不存在，将创建并设置其值。
```HMSET key field value [field value ...]```
`HMSET`命令在后续的版本中可能会被移除，请使用`HSET`命令代替。

* 示例
```
127.0.0.1:6379> HMSET hash k1 v1 k2 v2 k3 v3
OK
127.0.0.1:6379> HGETALL hash
1) "k1"
2) "v1"
3) "k2"
4) "v2"
5) "k3"
6) "v3"
7) "k4"
8) "v4"
```

* HMGET
`HMGET`命令用于获取哈希表中一个或多个域的值，并已列表的形式按给定的域名顺序将其返回。
```HMGET key field [field ...]```
当指定域名不存在时，返回的列表中对应的值将为`nil`。若哈希表不存在，执行命令时将会视为一个空哈希表，并返回`nil`值的列表。

* 示例
```
127.0.0.1:6379> HMGET hash k1 k2 k3 k4
1) "v1"
2) "v2"
3) "v3"
4) "v4"
```

##### 5.3.4 HKEYS / HVALS
* HKEYS
`HKEYS`命运用于获取指定哈希表中的所有键值对的键名，并以列表的形式返回。当哈希表为空时返回空列表。
```HKEYS key```

* 示例
```
127.0.0.1:6379> HKEYS hash
1) "k1"
2) "k2"
3) "k3"
4) "k4"
```

* HVALS
`HVALS`命运用于获取指定哈希表中的所有键值对的键值，并以列表的形式返回。当哈希表为空时返回空列表。
```HVALS key```

* 示例
```
127.0.0.1:6379> HVALS hash
1) "v1"
2) "v2"
3) "v3"
4) "v4"
```

##### 5.3.5 HLEN / HSTRLEN
* HLEN 
`HLEN`命令用户获取哈希表中存在的域的数量，当哈希表不存在时将返回0。
```HLEN key```

* 示例
```
127.0.0.1:6379> HLEN hash
(integer) 4
```

* HSTRLEN
`HSTRLEN`命令用于获取哈希表中指定域的值的长度，若哈希表或域不存在则返回0。
```HSTRLEN key field```

* 示例
```
127.0.0.1:6379> HSTRLEN hash k1
(integer) 2
```

##### 5.3.6 HEXISTS
* HEXISTS
`HEXISTS`命令用于获取哈希表中指定名称的域是否存在。
```HEXISTS key field```
当哈希表中存在指定域时返回1；当哈希表中不包含指定域或哈希表不存在时，返回0。

* 示例
```
127.0.0.1:6379> HEXISTS hash k1
(integer) 1
127.0.0.1:6379> HEXISTS hash k5
(integer) 0
```

##### 5.3.7 HINCRBY / HINCRBYFLOAT
* HINCRBY
`HINCRBY`命令与前文中介绍`INCRBY`命令类似，它用于对哈希表中指定域的值进行递增的操作，并返回执行递增操作后该域的值。若指定的域不存在，将在执行操作前将其值设置为0；若指定的哈希表不存在，则将创建对应的哈希表并执行操作。当指定的域的值无法表示为数值时，将返回错误。
与`INCRBY`命令相同，`HINCRBY`所支持的值为64位的有符号整数。若`incremnet`的值为负数值，其操作等同于做递减操作。
```HINCRBY key field increment```

* 示例
```
127.0.0.1:6379> HSET hash k5 1
(integer) 1
127.0.0.1:6379> HINCRBY hash k5 10
(integer) 11
127.0.0.1:6379> HGET hash k5
"11"
127.0.0.1:6379> HINCRBY hash k1 10
(error) ERR hash value is not an integer
```

* HINCRBYFLOAT
`HINCRBYFLOAT`命令用于将哈希表中对应域的值解释为浮点数类型，执行递增指定浮点数值的操作，并返回执行递增后的值。`HINCRBYFLOAT`命令使用与`INCRBYFLOAT`命令相似。
```HINCRBYFLOAT key field increment```

* 示例
```
127.0.0.1:6379> HINCRBYFLOAT hash k5 10.4
"21.4"
127.0.0.1:6379> HINCRBYFLOAT hash k5 -10.4
"11"
```

##### 5.3.8 HSCAN
* HSCAN
`HSCAN`命令用于增量式的迭代获取哈希表中的所有域，并返回其域名称及其值。相对于上文中介绍的`HGETALL`、`HKEYS`以及`HVALS`命令，在哈希表中域数量较多的情况下不会造成阻塞，进而相对更加安全。
```HSCAN key cursor [MATCH pattern] [COUNT count]```
与`SCAN`命令相同，`HSCAN`命令是一个基于游标`cursor`的迭代器，每次执行后将会返回一个新的游标，以作为下一轮迭代的游标参数。一次的迭代以游标`0`为开始，并在返回游标`0`时结束，称之为一次完整的迭代。同样，`HSCAN`命令也接受`MATCH`和`COUNT`两个参数，用于提供模式匹配的功能及限制获取的数量。

* 示例
```
127.0.0.1:6379> HSCAN hash 0
1) "0"
2)  1) "k1"
    2) "v1"
    3) "k2"
    4) "v2"
    5) "k3"
    6) "v3"
    7) "k4"
    8) "v4"
    9) "k5"
   10) "11"
```

##### 5.3.9 HDEL
* HDEL
`HDEL`命令用于移除哈希表中指定的域（Redis 2.4以上的版本支持同时移除一个或多个），并返回实际移除的域的个数。当指定的域不存在时将忽略该域不执行移除的操作；若哈希表不存在，将认为其为一个空哈希表并返回0。
```HDEL key field [field ...]```

* 示例
```
127.0.0.1:6379> HDEL hash k5
(integer) 1
127.0.0.1:6379> HKEYS hash 
1) "k1"
2) "k2"
3) "k3"
4) "k4"
```