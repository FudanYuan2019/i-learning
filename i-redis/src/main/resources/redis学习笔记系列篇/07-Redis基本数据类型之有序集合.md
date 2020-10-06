#### 5.5  有序集合(sorted-set)
##### 5.5.1 ZADD
* ZADD
`ZADD`命令用于将指定元素及其`score`值添加至有序集合中，并返回新增的元素个数（除使用`INCR`参数的情况外），在Redis 2.4及以上版本中可一次添加一个或多个元素。当添加的元素已存在时，将会更新其`score`值，并根据该值重新插入到对应的位置保持正确的顺序。
```ZADD key [NX|XX] [CH] [INCR] score member [score member ...]```
有序集合中元素的`score`值为一个64位浮点数值，并使用IEEE 754浮点数标准所表示。它可被表示为-(2^54) ~ (2^54)之间的整数，且可使用`+inf`及`-inf`分别表示为正负无穷大值。当有序集合中有多个元素的`score`值相同时，将按照字典序（lexicographically）进行排序，字典序即对应的单词在字典中的先后顺序。

* 参数
Redis 3.0.2及以上版本支持以下参数：
  *   `NX`：不更新已经存在的元素，只增加新元素。
  *   `XX`：只更新已经存在的元素，不增加新元素。
  *   `CH`：返回值由新增的元素个数变为修改的元素个数，修改的元素包括新增的元素和已存在元素中`score`值有更新的元素。
  *   `INCR`：使用该参数时执行的操作类似于`ZINCRBY`操作，对指定元素的`score`值执行递增操作，并返回更新后的`score`值。当操作执行失败时（如使用了`NX`或`XX`参数的情况下），将返回`nil`。
其中，`NX`与`XX`参数不能同时使用。另外，使用`INCR`参数时与其它情况较为不同，只允许设置一个元素，并返回更新后的`score`值。

* 示例
```
127.0.0.1:6379> ZADD zset NX 10.0 redis 5.0 mechached 
(integer) 2
127.0.0.1:6379> ZADD zset NX 11.0 redis
(integer) 0
127.0.0.1:6379> ZADD zset NX 8.5 mysql
(integer) 1
127.0.0.1:6379> ZADD zset XX CH 12.5 redis 3.5 mechached 
(integer) 2
127.0.0.1:6379> ZADD zset XX CH INCR 1.5 redis
"14"
```

##### 5.5.2 ZPOPMAX / ZPOPMIN / BZPOPMAX / BZPOPMIN
* ZPOPMAX
`ZPOPMAX`命令用于移除并返回有序集合中分值（即`score`值）最高的参数`count`所指定个数的元素。若`count`参数未指定，其值默认为1。当`count`的值大于集合中元素的数量，将移除并返回全部的元素而非返回错误。
```ZPOPMAX key [count]```

* 示例
```
127.0.0.1:6379> ZPOPMAX zset 2
1) "redis"
2) "14"
3) "mysql"
4) "8.5"
```

* ZPOPMIN
`ZPOPMIN`命令与`ZPOPMAX`命令相似，二者区别为`ZPOPMIN`用于移除有序集合中分值（即`score`的值）最小的元素。
```ZPOPMIN key [count]```

* 示例
```
127.0.0.1:6379> ZPOPMIN zset 2
1) "mechached"
2) "3.5"
```

* BZPOPMAX
和列表中的`BLPOP`等阻塞式的命令一样，有序集合也提供了`BZPOPMAX`与`BZPOPMIN`两个阻塞版的命令。
`BZPOPMAX`命令用于弹出指定有序集合中`score`最大的元素，它将返回第一个非空有序集合中`score`最大的元素，而非所有有序集合中最大的元素。当给定的有序集合都为空时，将阻塞连接直到超时或有元素可被弹出。
```BZPOPMAX key [key ...] timeout```
`BZPOPMAX`接受一个以秒为单位的整数值`timeout`，用于表示当给定的集合都为空时阻塞连接等待的时长。当`timeout`的值为0时，表示将一直等待直到有元素可被弹出为止。`timeout`值只接受0或正整数值，当`timeout`值为负数时将返回错误。

* 返回值
`BZPOPMAX`命令将返回一个包含三个元素的数组，分别为弹出元素的有序集合键名、被弹出的元素以及被弹出的元素的`score`值。
当给定的有序集合为空，且在给定`timeout`时间的内也没有元素可被弹出的情况下，将返回`nil`。

* BZPOPMIN
`BZPOPMIN`与`BZPOPMAX`命令类似，二者区别为`BZPOPMIN`返回第一个非空有序集合中`score`值最小的元素。关于`BZPOPMIN`命令更多的介绍，请参考上文中的`BZPOPMAX`命令。
```BZPOPMIN key [key ...] timeout```

* 示例
```
127.0.0.1:6379> ZADD zset NX 10.0 redis 5.0 mechached  8.5 mysql
(integer) 3
127.0.0.1:6379> BZPOPMAX zset 2
1) "zset"
2) "redis"
3) "10"
127.0.0.1:6379> BZPOPMIN zset 2
1) "zset"
2) "mechached"
3) "5"
127.0.0.1:6379> BZPOPMAX zset 2
1) "zset"
2) "mysql"
3) "8.5"
127.0.0.1:6379> BZPOPMAX zset 2
(nil)
(2.04s)
```

##### 5.5.3 ZSCORE
* ZSCORE
`ZSCORE`命令用于获取有序集合中指定元素的`score`值，并以字符串的形式返回。若集合中不存在该元素或集合不存在，则返回`nil`。
```ZSCORE key member```

* 示例
```
127.0.0.1:6379> ZSCORE zset redis
"10"
```

##### 5.5.4 ZCARD / ZCOUNT / ZLEXCOUNT
* ZCARD
`ZCARD`命令用于获取有序集合的基数（`cardinality`，即集合中元素的个数），若集合不存在则返回0。
```ZCARD key```

* 示例
```
127.0.0.1:6379> ZCARD zset
(integer) 3
```

* ZCOUNT
`ZCOUNT`命令用于获取`score`值在指定的范围内的元素个数。
```ZCOUNT key min max```
`ZCOUNT`的参数`min`和`max`可使用`-inf`和`+inf`表示正/负无穷，并使用`(`表示不包括参数所指定的值。

* 示例
```
127.0.0.1:6379> ZCOUNT zset 8 10
(integer) 2
127.0.0.1:6379> ZCOUNT zset -inf 10
(integer) 3
```

* ZLEXCOUNT
`ZLEXCOUNT`命令用于获取有序集合中以字典序排序指定范围内元素的数量。字典序的排序使用`memcmp()`函数实现，对集合中的元素进行逐个对比。字典序中，相同位置下较小的字符表示该成员小于另一成员，该位置字符相同的情况下将继续对比后续字符。例如，字符串`"a"`小于`"aa"`小于`"b"`。
使用`-`与`+`分别表示正负无穷大的字符串，命令`ZLEXCOUNT key - +`表示以字典序列出所有的元素。
另外，可以使用`(`以及`[`表示是否包括指定的字符串。其中，`(`表示为开区间，即端点不包括指定的字符串；`[`表示为闭区间，即端点包括指定的字符串。在示例中我们将为大家展示几个使用`(`与`[`的情况。
```ZLEXCOUNT key min max```

* 示例
```
127.0.0.1:6379> ZLEXCOUNT zset - +
(integer) 3
127.0.0.1:6379> ZLEXCOUNT zset [m +
(integer) 3
127.0.0.1:6379> ZLEXCOUNT zset [r +
(integer) 1
```

##### 5.5.5 ZINCRBY 
* ZINCRBY
`ZINCRBY`命令用于将有序集合中元素`score`的值增加`increment`参数所指定的值，并返回新值。若元素不存在，则认为其原值为0.0。
```ZINCRBY key increment member```
`increment`参数接受整型或浮点型的值，若该值为负数则将为该元素的`score`值减去`increment`的绝对值。

* 示例
```
127.0.0.1:6379> ZINCRBY zset -1.3 mysql
"7.2000000000000002"
127.0.0.1:6379> ZINCRBY zset 0.4 redis
"10.4"
```

##### 5.5.6 ZUNIONSTORE / ZINTERSTORE
* ZUNIONSTORE
`ZUNIONSTORE`命令用于计算由`numkeys`参数所指定数量的数个有序集合的并集，保存至`destination`参数所指定的有序集合中（若有序集合已存在将覆盖原有内容），并返回最终保存至`destination`指定集合中元素的个数。在默认情况下，最终结果集合中的元素的`score`值为其它几个集合中对应元素`score`值之和。
```ZUNIONSTORE destination numkeys key [key ...] [WEIGHTS weight [weight ...]] [AGGREGATE SUM|MIN|MAX]```
`ZUNIONSTORE`接受使用`WEIGHTS`选项指定各个有序集合中元素`score`值在计算中的权重，其后续权重值的数量与集合的数量相同，且按顺序一一对应。各个集合中元素的`score`值将乘以对应集合的权重值，再传递至聚合函数中。在默认情况下，每个集合默认的权重都为1。
例如指令```ZUNIONSTORE dest 3 zset1 zset2 zset3 WEIGHTS 3 2 1```中，有序集合`zset1`对应的权重值为3，在后续并集的计算中，其元素的`score`值都将乘以3后再传递给聚合函数处理。而`zset2`对应的权重值为2，`zset3`对应的权重值为1。
使用`AGGREGATE`选项，可指定并集的聚合操作。Redis提供了三种聚合操作`SUM`、`MIN`以及`MAX`可供选择，在默认情况下，将使用`SUM`聚合操作。
* 三种聚合操作代表：
  * SUM：各集合中对应元素的score值之和。
  * MIN：各集合对应元素的score值中的最小值。
  * MAX：各集合对应元素的score值中的最大值。

* 示例
```
127.0.0.1:6379> ZADD zset1 10.0 redis 5.0 mechached  8.5 mysql
(integer) 3
127.0.0.1:6379> ZADD zset2 0.4 redis 0.3 mechached 1 hbase
(integer) 3
127.0.0.1:6379> ZUNIONSTORE zset3 2 zset1 zset2 WEIGHTS 1 2 AGGREGATE SUM
(integer) 4
127.0.0.1:6379> ZSCORE zset3 redis
"10.800000000000001"
```

* ZINTERSTORE
`ZINTERSTORE`命令用于计算由`numkeys`参数所指定数量的数个有序集合的交集，保存至`destination`参数所指定的有序集合中（若有序集合已存在将覆盖原有内容），并返回最终保存至`destination`指定集合中元素的个数。在默认情况下，最终结果集合中的元素的`score`值为其它几个集合中对应元素`score`值之和。
```ZINTERSTORE destination numkeys key [key ...] [WEIGHTS weight [weight ...]] [AGGREGATE SUM|MIN|MAX]```

* 示例
```
127.0.0.1:6379> ZINTERSTORE zset4 2 zset1 zset2 AGGREGATE MIN
(integer) 2
127.0.0.1:6379> ZSCORE zset4 redis
"0.40000000000000002"
127.0.0.1:6379> ZSCORE zset4 mysql
(nil)
127.0.0.1:6379> ZSCORE zset4 mechached
"0.29999999999999999"
```

##### 5.5.7 ZRANGE / ZRANGEBYLEX / ZRANGEBYSCORE
* ZRANGE
`ZRANGE`命令用于获取有序集合中有`start`及`stop`参数所指定范围内的元素，并以其`score`值按从小到大的顺序返回。当存在多个元素`score`值相同时，将按字典序排列。
```ZRANGE key start stop [WITHSCORES]```
`start`及`stop`参数为以0为开始的索引数值，即0代表第一个元素，1代表第二个元素。当值为负数时，表示相对于有序集合末尾的的偏移值，即-1代表有序集合中最后一个元素，-2代表倒数第二个元素。`start`与`stop`所对应位置的元素都将被包含在返回的数据中，例如使用`ZRANGE key 0 1`将返回第一个和第二个元素。
当获取的返回超出有序集合的大小时不会产生错误。当`start`大于有序集合的大小或`start`的值大于`stop`的值时，将会返回一个空列表。当`stop`的值大于有序集合的大小时，将只会获取到最后一个元素为止。
当使用参数`WITHSCORES`时，返回的结果将包括各元素的`score`值，并以`value1, score1, ..., valueN, scoreN`的顺序返回。

* 示例
```
127.0.0.1:6379> ZRANGE zset4 0 -1
1) "mechached"
2) "redis"
127.0.0.1:6379> ZRANGE zset4 0 -1 WITHSCORES
1) "mechached"
2) "0.29999999999999999"
3) "redis"
4) "0.40000000000000002"
```

*ZRANGEBYLEX
`ZRANGEBYLEX`用于在有序集合中所有元素`score`值相同的情况下返回指定范围的以字典序从小到大排序的元素。若集合中元素存在着不同的`score`值，则返回的结果是不确定的。
```ZRANGEBYLEX key min max [LIMIT offset count]```
`ZRANGEBYLEX`也可以使用`LIMIT`选项指定返回结果的偏移以及数量。

* 示例
```
127.0.0.1:6379> ZRANGEBYLEX zset1 - + 
1) "mechached"
2) "hbase"
3) "mysql"
4) "redis"
```

* ZRANGEBYSCORE
`ZRANGEBYSCORE`命令用于获取有序集合中`score`值在`min`与`max`范围之间的元素，并以`score`值从低到高的顺序返回。当有多个元素的`score`值相同时将以字典序进行排序。
```ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count]```
`min`与`max`参数分别表示获取的元素中`score`的最小值与最大值。其中，`min`和`max`参数除了可以使用数值表示外，还可以有以下几种情况：
  * 分别使用-inf以及+inf表示正负无穷；
  * 使用(表示不包括参数所指定的值，例如：
      ZRANGEBYSCORE key (0 5表示0 < score <= 5
      ZRANGEBYSCORE key 0 (5表示0 <= score < 5
      ZRANGEBYSCORE key (0 (5表示0 < score < 5
当使用参数`LIMIT`时，表示获取从`offset`开始的数量为`count`的元素。`LIMIT`参数的使用类似与`SQL`中的`SELECT LIMIT offset, limit`语句，例如`ZRANGEBYSCORE key 0 5 LIMIT 1 2`表示获取有序集合中`score`值在0到5范围内以从低到高的顺序排序后第2个元素起的两个元素。当`count`的值为负数时，将返回剩下的所有元素。
与`ZRANGE`相同，使用`WITHSCORES`参数使返回的结果包含元素对应的`score`值。

* 示例
```
127.0.0.1:6379> ZADD zset1 10.0 redis 5.0 mechached  8.5 mysql 8.5 hbase
(integer) 4
127.0.0.1:6379> ZRANGEBYSCORE zset1 8.5 10 WITHSCORES LIMIT 2 10
1) "redis"
2) "10"
127.0.0.1:6379> ZRANGEBYSCORE zset1 8.5 10 WITHSCORES LIMIT 1 10
1) "mysql"
2) "8.5"
3) "redis"
4) "10"
127.0.0.1:6379> ZRANGEBYSCORE zset1 (8.5 10 WITHSCORES LIMIT 0 10
1) "redis"
2) "10"
```

##### 5.5.8 ZREVRANGE / ZREVRANGEBYLEX / ZREVRANGEBYSCORE
* ZREVRANGE
`ZREVRANGE`命令与`ZRANGE`命令相似，也用于列出有序集合中指定位置的元素，但其排列顺序为按`score`值从高到低。当多个元素`score`值相同时，将按字典序逆向排序。
```ZREVRANGE key start stop [WITHSCORES]```

* 示例
```
127.0.0.1:6379> ZREVRANGE zset1 0 -1 WITHSCORES
1) "redis"
2) "10"
3) "mysql"
4) "8.5"
5) "hbase"
6) "8.5"
7) "mechached"
8) "5"
```

* ZREVRANGEBYLEX
`ZREVRANGEBYLEX`命令与`ZRANGEBYLEX`命令相似，它同样在`score`值相同的情况下以字典序返回指定返回的结果，但结果为从大到小排序。
```ZREVRANGEBYLEX key max min [LIMIT offset count]```

* 示例
```
127.0.0.1:6379> ZREVRANGEBYLEX zset1 + - LIMIT 1 4
1) "mysql"
2) "hbase"
3) "mechached"
```

* ZREVRANGEBYSCORE
`ZREVRANGEBYSCORE`命令与`ZRANGEBYSCORE`命令相类似，二者区别为`ZREVRANGEBYSCORE`命令以`score`值从高到低的顺序返回。
```ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT offset count]```

* 示例
```
127.0.0.1:6379> ZREVRANGEBYSCORE zset1 10 8 WITHSCORES LIMIT 0 4
1) "redis"
2) "10"
3) "mysql"
4) "8.5"
5) "hbase"
6) "8.5"
127.0.0.1:6379> ZREVRANGEBYSCORE zset1 10 8 WITHSCORES LIMIT 1 4
1) "mysql"
2) "8.5"
3) "hbase"
4) "8.5"
```

##### 5.5.9 ZRANK / ZREVRANK
* ZRANK
`ZRANK`命令用于获取元素在有序集合中按`score`值从小到排序后的位置，并返回以0为基准的索引值。即当返回0时，表示对应元素的`score`值为有序集合中的最小值。当集合中不存在指定元素或集合不存在时将返回`nil`。
```ZRANK key member```

* 示例
```
127.0.0.1:6379> ZRANK zset1 redis
(integer) 3
```

* ZREVRANK
`ZREVRANK`与`ZRANK`命令类似，用于获取元素在有序集合中的位置，二者区别为`ZREVRANK`依照`score`值从大到小的顺序排列。
```ZREVRANK key member```

* 示例
```
127.0.0.1:6379> ZREVRANK zset1 redis
(integer) 0
```

##### 5.5.10 ZSCAN
* ZSCAN
`ZSCAN`命令用于增量式的迭代获取集合中的所有元素。同样，`ZSCAN`命令是一个基于游标`cursor`的迭代器，每次执行后将会返回一个新的游标，以作为下一轮迭代的游标参数。
```ZSCAN key cursor [MATCH pattern] [COUNT count]```

* 示例
```
127.0.0.1:6379> ZSCAN zset1 0
1) "0"
2) 1) "mechached"
   2) "5"
   3) "hbase"
   4) "8.5"
   5) "mysql"
   6) "8.5"
   7) "redis"
   8) "10"
```

##### 5.5.11 ZREM / ZREMRANGEBYLEX / ZREMRANGEBYRANK / ZREMRANGEBYSCORE
* ZREM
`ZREM`命令用于移除有序集合中的指定元素，并实际时间移除的元素个数。其中，不存在的元素将被忽略。当`key`对应的键存在但类型不为有序集合时，将返回错误。
```ZREM key member [member ...]```
在Redis 2.4及以上的版本中，`ZREM`支持一次移除多个元素。

* 示例
```
# mysql1 不存在， 因此只删除了hbase
127.0.0.1:6379> ZREM zset1 hbase mysql1
(integer) 1
```

## ZREMRANGEBYLEX
`ZREMRANGEBYLEX`命令用于移除有序集合中以字典序排序指定范围内的元素，并返回实际移除的元素数量。
```ZREMRANGEBYLEX key min max```

* 示例
```
127.0.0.1:6379> ZREMRANGEBYLEX zset1 [m [p
(integer) 2
127.0.0.1:6379> ZRANGE zset 0 -1 WITHSCORES
(empty array)
```

* ZREMRANGEBYRANK
`ZREMRANGEBYRANK`命令用于移除有序集合中按`score`值从小到大排序后参数`start`与`stop`所指定索引范围内的元素，并返回实际移除的元素个数。
```ZREMRANGEBYRANK key start stop```
当参数值为负数，代表按`score`值按从大到小排序后的索引值。例如-1代表`score`值最高的元素，-2代表`score`值第二高的元素。

* 示例
```
127.0.0.1:6379> ZRANGE zset1 0 -1 WITHSCORES
1) "mechached"
2) "5"
3) "hbase"
4) "8.5"
5) "mysql"
6) "8.5"
7) "redis"
8) "10"
127.0.0.1:6379> ZREMRANGEBYRANK zset1 0 2
(integer) 3
127.0.0.1:6379> ZRANGE zset1 0 -1 WITHSCORES
1) "redis"
2) "10"
```

* ZREMRANGEBYSCORE
`ZREMRANGEBYSCORE`命令用于移除有序集合中`score`值在参数`min`与`max`所指定范围内的元素，并返回实际移除的元素个数。
```ZREMRANGEBYSCORE key min max```
在一般情况下，`ZREMRANGEBYSCORE`的参数`min`与`max`所指定的范围包含值为二者的元素。为了实现不包含端点值的情况，可使用`(`标识，例如`ZREMRANGEBYSCORE key (1 5`表示移除`1 < score <= 5`的元素。

* 示例
```
127.0.0.1:6379> ZRANGE zset1 0 -1 WITHSCORES
1) "mechached"
2) "5"
3) "hbase"
4) "8.5"
5) "mysql"
6) "8.5"
7) "redis"
8) "10"
127.0.0.1:6379> ZREMRANGEBYSCORE zset1 5 9
(integer) 3
127.0.0.1:6379> ZRANGE zset1 0 -1 WITHSCORES
1) "redis"
2) "10"
```