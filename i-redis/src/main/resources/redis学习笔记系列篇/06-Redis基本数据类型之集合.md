#### 5.4  集合(set)
##### 5.4.1 SADD 
* SADD
`SADD`命令用于将指定元素添加到集合中，并返回实际添加的元素个数（即不包括已经存在的元素个数）。当指定的元素在集合中已经存在时，将忽略该元素。若指定的键不存在，在执行操作前将创建一个新的空集合。
```SADD key member [member ...]```
在Redis 2.4及以上版本中，`SADD`命令可用于一次添加多个元素。

* 示例
```
127.0.0.1:6379> SADD set a b c d
(integer) 4
127.0.0.1:6379> SMEMBERS set
1) "c"
2) "b"
3) "d"
4) "a"
```

##### 5.4.2 SPOP
* SPOP
`SPOP`命令用于随机地从集合中移除并返回元素，若集合不存在则返回`nil`。在Redis 3.2及以上的版本中，`SPOP`命令支持通过`count`参数指定获取的元素个数。若`count`的值大于集合的大小，将移除并返回集合中的全部元素。
```SPOP key [count]```

* 示例
```
127.0.0.1:6379> SPOP set 3
1) "d"
2) "a"
3) "c"
127.0.0.1:6379> SPOP set 5
1) "b"
```

##### 5.4.3 SMOVE
* SMOVE
`SMOVE`命令用于将源集合中的指定元素移至目标集合中，即将该元素从源集合中移除并在目标集合中添加，并返回1。当源集合中不存在指定的元素时，将不执行操作并返回0。
```SMOVE source destination member```
`SMOVE`命令具备原子性，即在执行时其它客户端的连接只会在源集合或目标集合中获取到该元素。

* 示例
```
127.0.0.1:6379> SADD set a b c d
(integer) 4
127.0.0.1:6379> SMOVE set set1 a 
(integer) 1
127.0.0.1:6379> SMOVE set set1 d
(integer) 1
127.0.0.1:6379> SMOVE set set1 e
(integer) 0
127.0.0.1:6379> SMEMBERS set
1) "c"
2) "b"
127.0.0.1:6379> SMEMBERS set1
1) "d"
2) "a"
```

##### 5.4.4 SMEMBERS / SISMEMBER
* SMEMBERS
`SMEMBERS`命令用于已数组的形式返回集合中的所有元素，若集合不能存在则视为空集合。
```SMEMBERS key```

* 示例
```
127.0.0.1:6379> SMEMBERS set1
1) "d"
2) "a"
```

* SISMEMBER
`SISMEMBER`命令用于指示集合中是否存在指定元素，若存在则返回1，否则返回0（若集合不存在则视为空集合，也将返回0）。
```SISMEMBER key member```

* 示例
```
127.0.0.1:6379> SMEMBERS set
1) "c"
2) "b"
127.0.0.1:6379> SISMEMBER set e
(integer) 0
127.0.0.1:6379> SISMEMBER set c
(integer) 1
```

##### 5.4.5 SDIFF / SDIFFSTORE
* SDIFF
`SDIFF`命令用于获取指定集合（即第一个参数指定）与其它集合的差集。需要注意的是并非获取全部集合的差集，二者存在部分差异。若集合不存在将被视为空集合。
```SDIFF key [key ...]```

* 示例
```
127.0.0.1:6379> SADD set1 a b c d 
(integer) 4
127.0.0.1:6379> SADD set2 b d e f
(integer) 4
127.0.0.1:6379> SDIFF set1 set2
1) "c"
2) "a"
```

* SDIFFSTORE
SDIFFSTORE命令与SDIFF命令相似，区别在于SDIFFSTORE命令不直接返回其差集，而是保存到destination参数指定的集合中，并返回结果的数量。若destination参数指定的集合已存在将会被覆盖。
```SDIFFSTORE destination key [key ...]```

* 示例 
```
127.0.0.1:6379> SDIFFSTORE set3 set1 set2
(integer) 2
127.0.0.1:6379> SMEMBERS set3
1) "c"
2) "a"
```

##### 5.4.6 SUNION / SUNIONSTORE
* SUNION
`SUNION`命令用于获取指定集合的并集。若集合不存在将被视为空集合。
```SUNION key [key ...]```

* 示例
```
127.0.0.1:6379> SUNION set1 set2
1) "c"
2) "e"
3) "f"
4) "a"
5) "b"
6) "d"
```

* SUNIONSTORE
`SUNIONSTORE`命令与`SUNION`命令相似，区别在于`SUNIONSTORE`命令不直接返回其并集，而是保存到`destination`参数指定的集合中，并返回结果的数量。若`destination`参数指定的集合已存在将会被覆盖。
```SUNION key [key ...]```

* 示例
```
127.0.0.1:6379> SUNIONSTORE set3 set1 set2
(integer) 6
127.0.0.1:6379> SMEMBERS set3
1) "c"
2) "e"
3) "f"
4) "a"
5) "b"
6) "d"
```

##### 5.4.7 SINTER / SINTERSTORE
* SINTER
`SINTER`命令用于获取指定集合的交集。当集合不存在将被视为空集合，若参数中包含空集合，返回的结果也为空（任何集合与空集合的交集为空集）。
当只指定一个集合作为参数时，执行该命令等同于执行`SMEMBERS`命令。
```SINTER key [key ...]```

* 示例
```
127.0.0.1:6379> SINTER set1
1) "c"
2) "b"
3) "d"
4) "a"
127.0.0.1:6379> SINTER set1 set2
1) "b"
2) "d" 
```

* SINTERSTORE
`SINTERSTORE`命令与`SINTER`命令相似，区别在于`SINTERSTORE`命令不直接返回其交集，而是保存到`destination`参数指定的集合中，并返回结果的数量。若`destination`参数指定的集合已存在将会被覆盖。
```SINTERSTORE destination key [key ...]```

* 示例
```
127.0.0.1:6379> SINTERSTORE set3 set1 set2
(integer) 2
127.0.0.1:6379> SMEMBERS set3
1) "b"
2) "d"
```

##### 5.4.8 SCARD
`SCARD`命令用于返回集合的基数（`cardinality`，即集合中元素的个数），若集合不存在则将返回0。
```SCARD key```

* 示例
```
127.0.0.1:6379> SCARD set1
(integer) 4
127.0.0.1:6379> SCARD set4
(integer) 0
```

##### 5.4.9 SRANDMEMBER
* SRANDMEMBER
`SRANDMEMBERS`命令用于随机获取元素，并在Redis 2.6及以上版本中支持通过`count`参数指定获取的元素个数。
```SRANDMEMBER key [count]```
对于`count`参数，不同的值将有以下几种情况：
  * 若其值为正数则随机返回集合中指定数量的元素，且所有元素不重复；
  * 若其值大于集合的大小则返回集合中的全部元素；
  * 若其值为负数，则将随机获取该值绝对值数量的元素，且可能存在重复的元素；
  * 若其值为0，则返回空数组。

* 返回值
当未指定count参数时：
  * 随机返回集合内的一个元素；
  * 若集合不存在则返回nil。

  当通过count参数指定获取的元素个数时：
  * 返回随机获取的元素数组；
  * 若集合不存在则返回空数组。

* 示例
```
127.0.0.1:6379> SMEMBERS set1
1) "c"
2) "b"
3) "d"
4) "a"
127.0.0.1:6379> SRANDMEMBER set1 2
1) "b"
2) "c"
127.0.0.1:6379> SRANDMEMBER set1 2
1) "a"
2) "d"
127.0.0.1:6379> SRANDMEMBER set1 -3
1) "d"
2) "c"
3) "d"
127.0.0.1:6379> SRANDMEMBER set1 -3
1) "c"
2) "c"
3) "c"
127.0.0.1:6379> SRANDMEMBER set1 0
(empty array)
```

##### 5.4.10 SSCAN
* SSCAN
`SSCAN`命令用于增量式的迭代获取集合中的所有元素。同样，`SSCAN`命令是一个基于游标`cursor`的迭代器，每次执行后将会返回一个新的游标，以作为下一轮迭代的游标参数。
```SSCAN key cursor [MATCH pattern] [COUNT count]```

* 示例
```
127.0.0.1:6379> SSCAN set1 0
1) "0"
2) 1) "c"
   2) "b"
   3) "d"
   4) "a"
```

##### 5.4.11 SREM
* SREM
`SREM`命令用于移除集合中的指定元素，并返回实际移除的元素个数。当集合中不存在指定的元素时将忽略对应的移除操作；若集合不存在，则将视为空集合，并返回0。
```SREM key member [member ...]```
在Redis 2.4及以上版本中，`SREM`命令可用于一次移除多个元素。

* 示例
```
127.0.0.1:6379> SSCAN set1 0
1) "0"
2) 1) "c"
   2) "b"
   3) "d"
   4) "a"
127.0.0.1:6379> SREM set1 a b c
(integer) 3
127.0.0.1:6379> SMEMBERS set1
1) "d"
127.0.0.1:6379> SREM set1 a b c e
(integer) 0
127.0.0.1:6379> SMEMBERS set1
1) "d"
127.0.0.1:6379> SREM set1 a b c d
(integer) 1
127.0.0.1:6379> SMEMBERS set1
(empty array) 
```

