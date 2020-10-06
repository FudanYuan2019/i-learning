#### 5.2 列表(list)
##### 5.2.1 LPUSH / RPUSH / LPUSHX / RPUSHX / LINSERT
* LPUSH / RPUSH
`LPUSH`与`RPUSH`命令用于将元素插入队列中，并在Redis 2.4以上版本支持一次将一个或多个元素插入队列中。两个命令的区别为`LPUSH`将新元素插入到队列的队首位置，而`RPUSH`命令将元素插入到队尾位置。命令执行后将返回插入元素后队列的长度。当键不存在，将创建一个空白的队列并执行插入操作；若键存在但不为队列，将返回错误。
```LPUSH key element [element ...]```
```RPUSH key element [element ...]```
当使用`LPUSH`插入多个元素时，将逐个将元素插入到队首。如插入`a、b、c`后，队列中的顺序为`c、b、a`。
当使用`RPUSH`插入多个元素时，将逐个将元素插入到队尾。如插入`a、b、c`后，队列中的顺序为`a、b、c`。

* 示例
```
# 将a b c 依次插入队首
127.0.0.1:6379> LPUSH list1 a b c
(integer) 3
127.0.0.1:6379> LRANGE list1 0 -1
1) "c"
2) "b"
3) "a"
# 将d e f 依次插入队尾
127.0.0.1:6379> RPUSH list2 d e f
(integer) 3
127.0.0.1:6379> LRANGE list2 0 -1
1) "d"
2) "e"
3) "f"
```

* LPUSHX / RPUSHX
`LPUSHX` / `RPUSHX`命令与`LPUSH` / `RPUSH`类似，区别为它们仅在键已存在且为列表的情况下才执行插入的操作。当键不存在时，将无操作被执行；当键存在但不为列表类型时将返回错误。
```LPUSHX key element [element ...]```
```RPUSHX key element [element ...]```
`LPUSHX` 与 `RPUSHX` 命令在执行后，将返回列表的长度。若键不存在则返回0。

* 示例
```
# list1 存在，因此插入成功，返回6
127.0.0.1:6379> LPUSHX list1 d e f
(integer) 6
127.0.0.1:6379> LRANGE list1 0 -1
1) "f"
2) "e"
3) "d"
4) "c"
5) "b"
6) "a"
# list 不存在，因此插入失败，返回0
127.0.0.1:6379> RPUSHX list d e f
(integer) 0
```

* LINSERT
`LINSERT`命令用于将元素插入到指定元素`pivot`之前或之后的位置，并返回插入元素后列表的长度。当元素`pivot`不存在时，将返回-1。若键不存在将视作其为一个空的列表，不执行任何操作。
`LINSERT key BEFORE|AFTER pivot element`

* 示例
```
127.0.0.1:6379> LPUSH list a b c
(integer) 3
127.0.0.1:6379> LINSERT list BEFORE a 1
(integer) 4
127.0.0.1:6379> LRANGE list 0 -1
1) "c"
2) "b"
3) "1"
4) "a"
```

##### 5.2.2 LPOP / RPOP / BLPOP / BRPOP
* LPOP / RPOP
`LPOP`命令用于移除并返回队列中的第一个元素，当键不存在时返回`nil`。当列表中只有一个元素时，执行`LPOP`命令后键将被删除。`RPOP`命令与`LPOP`相似，用于移除并返回列表中的最后一个元素。
```LPOP key```
```RPOP key```

* 示例
```
127.0.0.1:6379> LPUSH list a b c
(integer) 3
127.0.0.1:6379> LRANGE list 0 -1
1) "c"
2) "b"
3) "a"
127.0.0.1:6379> LPOP list
"c"
127.0.0.1:6379> RPOP list
"a"
127.0.0.1:6379> LPOP list
"b"
127.0.0.1:6379> LRANGE list 0 -1
(empty array)
```

* BLPOP / BRPOP
`BLPOP`命令是阻塞版的`LPOP`，当给定列表中无元素可被弹出时将阻塞连接，直到超时或有元素可被弹出。与`LPOP`不同的是，`BLPOP`可以给定多个键名，并将依次检查各个列表。若给定的键名中存在至少一个为非空列表，执行`BLPOP`将弹出并返回第一个非空列表中的队首元素。
`BRPOP`命令与`BLPOP`相似，即为阻塞版的`RPOP`操作，其与`BLPOP`的区别为存在非空列表时将从队尾弹出元素。
```BLPOP key [key ...] timeout```
```BRPOP key [key ...] timeout```
`BLPOP`/`BRPOP`参数中的`timeout`为一个以秒为单位的数值，用于表示阻塞的最长时间。当`timeout`的值为`0`时，表示将无限期地阻塞，直到有元素可被弹出。
`BLPOP`/`BRPOP`命令执行后将返回一个数组，其第一个元素为键名，第二个元素为被弹出的元素。当列表直到超时也无元素可被弹出时，返回`nil`。

* 示例
```
127.0.0.1:6379> LPUSH list a b c
(integer) 3
127.0.0.1:6379> BLPOP list 1
1) "list"
2) "c"
127.0.0.1:6379> BLPOP list 1
1) "list"
2) "b"
127.0.0.1:6379> BLPOP list 1
1) "list"
2) "a"
127.0.0.1:6379> BLPOP list 1
(nil)
(1.10s)
# 打开一个新的redis-cli，插入元素后，返回
127.0.0.1:6379> BLPOP list 0
1) "list"
2) "c"
(27.70s)
```

##### 5.2.3 RPOPLPUSH / BRPOPLPUSH
* RPOPLPUSH
`RPOPLPUSH`命令用于将`source`所指定的列表中队尾位置的元素移除，并插入到`destination`所指定的列表队首位置。该命令执行后将返回从`source`中移除并插入到`destination`中的元素。
```RPOPLPUSH source destination```
若`source`所指定的列表不存在，将返回`nil`且不执行其它操作。若`destination`所指定的列表不存在，则将创建一个空队列并执行插入操作。
若`source`与`destination`相同，执行后的结果将为移除队尾的元素并插入到队首。
* 示例
```
127.0.0.1:6379> LPUSH list1 a b c
(integer) 3
127.0.0.1:6379> RPOPLPUSH list1 list2
"a"
127.0.0.1:6379> LRANGE list1 0 -1
1) "c"
2) "b"
127.0.0.1:6379> LRANGE list2 0 -1
1) "a"
127.0.0.1:6379> RPOPLPUSH list1 list1
"b"
127.0.0.1:6379> LRANGE list1 0 -1
1) "b"
2) "c"
```

* BRPOPLPUSH
`BRPOPLPUSH`命令即阻塞版的`RPOPLPUSH`命令，当`source`指定的列表不为空时（列表中存在元素），其行为与`RPOPLPUSH`命令相同。
```BRPOPLPUSH source destination timeout```
若执行命令时列表不为空，执行操作后返回与`RPOPLPUSH`命令相同。若等待超时（在`timeout`时间内无元素可被弹出），则将返回`nil`及等待的时间。若在等待期间存在元素可被弹出，则在执行操作后返回一个数组，其中包括被弹出元素的值及等待的时间。

* 示例
```
127.0.0.1:6379> BRPOPLPUSH list1 list2 5
"c"
127.0.0.1:6379> BRPOPLPUSH list1 list2 5
"b"
127.0.0.1:6379> BRPOPLPUSH list1 list2 5
(nil)
(5.00s)
127.0.0.1:6379> BRPOPLPUSH list1 list2 0
"a"
(4.70s)
```

##### 5.2.4 LRANGE / LPOS / LINDEX / LLEN
* LRANGE
`LRANGE`命令用于获取列表中由索引值`start`及`stop`指定的范围内的元素，索引值从0开始（即0为列表中第一个元素），负数值表示相对于列表队尾的位置（如-1代表列表中最后一个元素，-2代表列表中倒数第二个元素）。`LRANGE`命令返回的元素将包含`start`及`stop`范围内的所有元素，如使用`LRANGE list 0 10`将获取到索引为0到10的总共11个元素。
```LRANGE key start stop```
当索引超出列表边界时，将只返回范围内列表存在的元素，而不会返回错误。若偏移`start`大于队尾的位置，将返回空列表（即无元素）；若偏移`stop`大于队尾的位置，将认为只获取到列表队尾的位置。

* 示例
```
127.0.0.1:6379> LPUSH list a b c
(integer) 3
# 从第0个到倒数第1个，即所有元素
127.0.0.1:6379> LRANGE list 0 -1
1) "c"
2) "b"
3) "a"
127.0.0.1:6379> LRANGE list 0 1000
1) "c"
2) "b"
3) "a"
127.0.0.1:6379> LRANGE list -2 -1
1) "b"
2) "a"
127.0.0.1:6379> LRANGE list -2 0
(empty array)
```

* LPOS
`LPOS`命令返回列表中符合条件的元素的索引，返回的索引从`0`开始（即`0`为列表中的第一个元素。在未指定参数的情况下，默认从列表的队首开始扫描，直到找到符合的元素。
```LPOS key element [RANK rank] [COUNT num-matches] [MAXLEN len]```
`LPOS`有三个可选参数`RANK`、`COUNT`以及`MAXLEN`：
    *   `RANK` 获取第N个符合条件元素；
    *   `COUNT` 获取的元素数量；
    *   `MAXLEN` 只比较给定数量的元素。

* 示例
```
127.0.0.1:6379> LPUSH list a b c a d e f
(integer) 7
127.0.0.1:6379> LPOS list a RANK 1
(integer) 3
127.0.0.1:6379> LPOS list a RANK 2
(integer) 6
127.0.0.1:6379> LPOS list a COUNT 2
1) (integer) 3
2) (integer) 6
# 从第2个a开始，输出3个a的位置。
127.0.0.1:6379> LPOS list a RANK 2 COUNT 3
1) (integer) 6
# 获取前7个元素中的3个a的位置列表，只有2个a，因此，返回 3 和 6
127.0.0.1:6379> LPOS list a COUNT 3 MAXLEN 7
1) (integer) 3
2) (integer) 6
# 获取前4个元素中的3个a的位置列表，只有1个a，因此，返回 3
127.0.0.1:6379> LPOS list a COUNT 3 MAXLEN 4
1) (integer) 3
```

* LINDEX
`LINDEX`命令用于获取列表中指定索引的元素，当索引超出列表范围时返回`nil`。索引的值从0开始，即0代表列表中的第一个元素，1代表列表中的第二个元素。当索引的值为负数时，表示相对于列表队尾的位置，如-1代表列表中最后一个元素，-2代表列表中倒数第二个元素。
```LINDEX key index```

* 示例
```
127.0.0.1:6379> LPUSH list a b c
(integer) 3
127.0.0.1:6379> LINDEX list 0
"c"
127.0.0.1:6379> LINDEX list 1
"b"
127.0.0.1:6379> LINDEX list -1
"a"
127.0.0.1:6379> LINDEX list 5
(nil)
```

* LLEN
`LLEN`命令用于获取列表中存储的元素数量，若键不存在则当作空白的队列（即返回0）。当键存在但类型不为队列时将返回错误。
```LLEN key```

* 示例
```
127.0.0.1:6379> LPUSH list a b c
(integer) 3
127.0.0.1:6379> LLEN list
(integer) 3
127.0.0.1:6379> LLEN list1
(integer) 0
127.0.0.1:6379> SET k1 v1
OK
127.0.0.1:6379> LLEN k1
(error) WRONGTYPE Operation against a key holding the wrong kind of value
```

##### 5.2.5 LTRIM 
* LTRIM 
`LTRIM`命令用于对列表进行修建，即只保留指定范围内的部分元素。指定范围的偏移量`start`及`stop`都为以0为开始，即索引0为列表中的第一个元素，负数值表示相对于列表队尾的位置（如-1代表列表中最后一个元素）。例如命令`LTRIM list 0 4`在执行后只保留列表中的前五个元素，其它元素将被删除。
```LTRIM key start stop```
当`start`的值大于列表的长度或`start`大于`stop`时，结果将为一个空列表。若`stop`大于列表的长度，其值将被设置为列表最后一个元素的索引值。
使用`LTRIM`与`LPUSH`或`RPUSH`命令配合使用，将达到使列表长度保持在指定大小内的效果。

* 示例
```
127.0.0.1:6379> LPUSH list a b c d
(integer) 4
127.0.0.1:6379> LPUSH list e f g
(integer) 7
127.0.0.1:6379> LTRIM list 0 5
OK
127.0.0.1:6379> LRANGE list 0 -1
1) "g"
2) "f"
3) "e"
4) "d"
5) "c"
6) "b"
127.0.0.1:6379> LPUSH list h i j
(integer) 9
127.0.0.1:6379>  LTRIM list 0 5
OK
127.0.0.1:6379> LRANGE list 0 -1
1) "j"
2) "i"
3) "h"
4) "g"
5) "f"
6) "e"
127.0.0.1:6379> LTRIM list 100 10000
OK
127.0.0.1:6379> LRANGE list 0 -1
(empty array)
```

##### 5.2.6 LSET
*LSET 
`LSET`命令用于设置列表中指定位置的元素，当索引不在列表的范围内时将返回错误。与`LINDEX`相同，索引从0开始，且负数值代表相对于队尾的位置。
```LSET key index element```

* 示例
```
127.0.0.1:6379> LPUSH list a b c
(integer) 3
127.0.0.1:6379> LSET list 0 d
OK
127.0.0.1:6379> LSET list -1 f
OK
127.0.0.1:6379> LRANGE list 0 -1
1) "d"
2) "b"
3) "f"
```

##### 5.2.7 LREM
* LREM
`LREM`命令用于移除队列中指定数量与参数`element`值相同的元素，并返回实际移除的元素数量。若键不存在，则是作为一个空列表，执行后将返回0（即无符合的元素被删除）。
```LREM key count element```
`LREM`命令执行的操作受到count参数影响，不同的count值执行的操作为：
    * count > 0时，将从队首开始移除对应数量相同的元素；
    * count < 0时，将从队尾开始移除对应数量相同的元素；
    * count = 0，将移除列表中所有相同的元素。

* 示例
```
127.0.0.1:6379> LPUSH list a b a a c d e d
(integer) 8
# 移除所有a
127.0.0.1:6379> LREM list 0 a
(integer) 3
127.0.0.1:6379> LRANGE list 0 -1
1) "d"
2) "e"
3) "d"
4) "c"
5) "b"
# 从队首开始移除1个d
127.0.0.1:6379> LREM list 1 d
(integer) 1
127.0.0.1:6379> LRANGE list 1 -1
1) "d"
2) "c"
3) "b"
```