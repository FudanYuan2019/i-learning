### 5. Redis数据类型
Redis支持字符串(string)、列表(list)、哈希(hash)、集合(set)及有序集合(sorted set) 5 种数据类型。
#### 5.1 字符串(string) 
##### 5.1.1  SET / SETEX / PSETEX / SETNX
* SET
SET命令为指定名称的键设置字符串类型的值，若键已存在则覆盖旧值（未指定特定参数的情况下）。
```SET key value [EX seconds|PX milliseconds] [NX|XX] [KEEPTTL]```
* 可选参数
`SET`命令有`EX`、`PX`、`NX`、`XX`以及`KEEPTTL`五个可选参数，其中`KEEPTTL`为6.0版本添加的可选参数，其它为2.6.12版本添加的可选参数。
`EX seconds 以秒为单位设置过期时间`
`PX milliseconds 以毫秒为单位设置过期时间`
`NX 键不存在的时候设置键值`
`XX 键存在的时候设置键值`
`KEEPTTL 保留设置前指定键的生存时间`
`SET`命令使用`EX`、`PX`、`NX`参数，其效果等同于`SETEX`、`PSETEX`、`SETNX`命令。根据官方文档的描述，未来版本中`SETEX`、`PSETEX`、`SETNX`命令可能会被淘汰。
* 返回值
设置成功则返回`OK`；返回`nil`为未执行`SET`命令，如不满足`NX`、`XX`条件等。
* 示例
```
# 设置k1的值为v1
127.0.0.1:6379>  SET k1 v1
OK
# 获取k1的值
127.0.0.1:6379> GET k1
"v1"
# 当键k1不存在时，设置k1的值为v2
127.0.0.1:6379> SET k1 v2 NX
(nil)
# 因为使用了NX参数，更新失败，返回v1
127.0.0.1:6379> GET k1
"v1"
# 在k2不存在的条件下，设置k2的值为v2，过期时间为5s
127.0.0.1:6379> SET k2 v2 EX 5 NX
OK
# 5s内获取k2的值，返回v2
127.0.0.1:6379> GET k2
"v2"
# 5s后获取k2的值，返回nil
127.0.0.1:6379> GET k2
(nil)
```

* SETEX
`SETEX`命令为指定名称的键设置值，并以秒为单位设置其生存时间。
`SETEX key seconds value`
`SETEX`命令效果等同于使用`SET key value`及`EXPIRE key seconds`命令，以及`SET key value EX seconds`命令。`SETEX`命令具备原子性，它等同于在`MULTI/EXEC`块中使用`SET`以及`EXPIRE`命令。
* 示例
```
# 设置k1的过期时间为5s
127.0.0.1:6379> SETEX k1 5 v1
OK
# 等同于
127.0.0.1:6379> SET k1 v1 EX 5
OK
# 等同于
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> SET k1 v1
QUEUED
127.0.0.1:6379> EXPIRE k1 5
QUEUED
127.0.0.1:6379> EXEC
1) OK
2) (integer) 1
```

* PSETEX
`PSETEX`命令与`SETEX`命令相似，二者区别为`PSETEX`设置的生存时间以毫秒作为单位。
`PSETEX key milliseconds value`
* 示例
```
# 设置k2的过期时间为5000ms
127.0.0.1:6379> PSETEX k2 5000 v2
OK
127.0.0.1:6379> GET k2
"v2"
127.0.0.1:6379> GET k2
"v2"
127.0.0.1:6379> GET k2
(nil)
```

* SETNX
当指定名称的键不存在时设置字符串值，否则不执行操作。其效果等同于在键不存在时直接使用`SET`命令，或是在任意情况下使用`NX`参数。
`SETNX key value`
* 返回值
当成功设置键值时返回1，否则返回0（即键已存在的情况下）。
* 示例
```
# 当k1不存在时，设置值为v1，返回1，表示设置成功
127.0.0.1:6379> SETNX k1 v1
(integer) 1
# 此时k1已存在，因此返回 0
127.0.0.1:6379> SETNX k1 v2
(integer) 0
127.0.0.1:6379> GET k1
"v1"
# 等同于
127.0.0.1:6379> SET k1 v2 NX
(nil)
127.0.0.1:6379> GET k1
"v1"
```

##### 5.1.2 GET
* GET
`GET`命令为获取指定名称键的键值。
```GET key```
* 返回值
当键存在且值为字符串时返回对应的值；
当键不存在时返回`nil`；
当键值不为字符串时返回错误。
* 示例
```
# 设置k1的值为v1
127.0.0.1:6379>  SET k1 v1
OK
# 获取k1的值，返回v1
127.0.0.1:6379> GET k1
"v1"
# 获取k2的值，返回nil
127.0.0.1:6379> GET k2
(nil)
```

##### 5.1.3 GETSET
* GETSET
`GETSET`命令用于设置键值对的值并返回旧值。
* 返回值
若键值对存在则返回旧值；
若键值对不存在则返回`nil`；
若键存在但不为字符串类型，则返回错误。
* 示例
```
# 清空数据库中的所有key
127.0.0.1:6379> FLUSHALL
OK
# 设置k1的值为v2，由于k1不存在，因此返回nil
127.0.0.1:6379> GETSET k1 v2
(nil)
# 设置k1的值为v1
127.0.0.1:6379> SET k1 v1
OK
# 获取k1的值，返回v1
127.0.0.1:6379> GET k1
"v1"
# 设置k1的值为v2，并返回旧值v1
127.0.0.1:6379> GETSET k1 v2
"v1"
# 获取k1的新值，返回v2
127.0.0.1:6379> GET k1
"v2"
```

##### 5.1.4 MSET/MGET/MSETNX
* MSET
`MSET`命令用于设置一个或多个键值对，该命令永远返回`OK`。`MSET`与`SET`命令相同，都会替代存在的键的值。
`MSET key value [key value ...]`
`MSET`命令具有原子性，所有的键都会一起被设置。其不存在一部分键值被更新，另一部分仍为旧值的情况。
* MGET 
`MGET`用于获取所有指定的键值。当某个键不存在时，将返回一个特殊的值nil。
`MGET key [key ...]`
* MSETNX
`MSETNX`命令用于设置一个或多个键值对，仅当所有键都不存在时才会执行。同样，`MSETNX`也具备原子性，所有的键会被一起被设置。
`MSETNX key value [key value ...]`
* 返回值
当所有的键被设置，则返回1
当所有的键都没有被设置，即至少一个键已存在的情况，则返回0
* 示例
```
127.0.0.1:6379> FLUSHALL
# 批量设置k1 k2 k3的值，返回OK
127.0.0.1:6379> MSET k1 v1 k2 v2 k3 v3
OK
# 批量获取k1 k2 k3 的值，返回 v1 v2 v3
127.0.0.1:6379> MGET k1 k2 k3
1) "v1"
2) "v2"
3) "v3"
# 修改k1的值为v2，但由于k1存在，导致此次设置的键全部失败，返回 0 
127.0.0.1:6379> MSETNX k1 v2 k4 v4 k5 v5
(integer) 0
# k4 k5 并未成功设置，因此返回 nil
127.0.0.1:6379> MGET k1 k2 k3 k4 k5
1) "v1"
2) "v2"
3) "v3"
4) (nil)
5) (nil)
```
##### 5.1.5 GETRANGE / SETRANGE
* GETRANGE
`GETRANGE`命令的作用为获取字符串值中由`start`及`end`参数指定范围的子串（包含`start`及`end`位置的字符）。当偏移值为负数时，指相对于字符串结尾的偏移量。例如当值为`redis`时，偏移量-1指的是最后一个字符`s`，偏移量-2指的是倒数第二个字符`i`。
当偏移量超出字符串的长度时，将会忽略超出的部分。例如值为`redis`时，获取偏移量从0到10的子串，仍将只返回`redis`。
`GETRANGE key start end`
`GETRANGE`命令在Redis 2.4.0中被添加，用于替代2.0之前版本中的`SUBSTR`命令。

* 示例
```
127.0.0.1:6379> SET k1 helloworld
OK
# 获取k1值的第0位到最后一位
127.0.0.1:6379> GETRANGE k1 0 -1
"helloworld"
# 获取k1值的第2位到倒数第2位
127.0.0.1:6379> GETRANGE k1 2 -2
"lloworl"
# 当偏移量超出字符串长度时，返回原始字符串
127.0.0.1:6379> GETRANGE k1 0 100
"helloworld"
# 当偏移量错误时，返回为空
127.0.0.1:6379> GETRANGE k1 -2 2
""
```

* SETRANGE
`SETRANGE`命令用于覆盖字符串中从偏移量开始的子串，并返回修改后的字符串长度。若偏移量大于原字符串的长度，将使用0值（数值0，非字符0）填充。
`SETRANGE key offset value`
由于Redis中字符串的最大长度为512 MB（2^29），所以偏移量的最大值为`536870911`（2^29 - 1）。

* 示例
```
127.0.0.1:6379> SETRANGE k1 5 WORLD
(integer) 10
127.0.0.1:6379> GET k1
"helloWORLD"
```

#####  5.1.6 INCR / INCRBY / DECR / DECRBY / INCRBYFLOAT
当字符串的值可表示为一个数值时，可使用`INCR`、`INCRBY`、`DECR`、`DECRBY`以及`INCRBYFLOAT`命令进行递增或递减的操作。
Redis中并不存在专门的整数类型，它们将以**字符串**的形式被储存，所以我们上面提到的几个命令也都是字符串的操作命令。在执行这些命令前，Redis会将对应的字符串解析为对应的64位有符号整数。若键值不为字符串或字符串无法被解析为指定范围的整数，将会返回错误。同样，执行操作得到的结果也必须为在64为有符号整数所内表示的范围内。

* INCR / INCRBY
`INCR`命令的作用为将字符串对应的整数值递增1，并返回递增后的整型值。在执行该命令前，若键不存在，则将自动创建并设置其值为0。
`INCR key`
`INCRBY`命令与`INCR`类似，二者区别为`INCRBY`命令可指定递增的值，且该值可为在可表示范围内的任意整数值。
`INCRBY key increment`
* 示例
```
# 设置k1的值为1
127.0.0.1:6379> SET k1 1
OK
# 将k1的值增加1
127.0.0.1:6379> INCR k1
(integer) 2
# 将k1的值增加1
127.0.0.1:6379> INCR k1
(integer) 3
# 将k1的值增加3
127.0.0.1:6379> INCRBY k1 3
(integer) 6
# 设置k2的值为v2，无法解析为整数，报错
127.0.0.1:6379> SET k2 v2
OK
127.0.0.1:6379> INCR k2
(error) ERR value is not an integer or out of range
# 设置k3的值为9223372036854775806
127.0.0.1:6379> SET k3 9223372036854775806
OK
# 将k3的值加1
127.0.0.1:6379> INCR k3
(integer) 9223372036854775807
# 将k3的值再加1，溢出，报错
127.0.0.1:6379> INCR k3
(error) ERR increment or decrement would overflow
```

* DECR / DECRBY
`DECR`、`DECRBY`命令的作用以及使用方法与上一节中的`INCR`、`INCRBY`相似，区别为这两个命令做的是递减的操作。同样，`DECR`、`DECRBY`命令也受64位有符号整数范围的限制。
`DECR key`
`DECRBY key decrement`
`INCRBY`的递增值以及`DECRBY`的递减值都可为负数，所以二者的也可被相互替代。例如`INCRBY key 1`等价于`DECRBY key -1`。

* 示例
```
# 将k1的值减1，返回5
127.0.0.1:6379> DECR k1 
(integer) 5
# 将k1的值减1，返回4
127.0.0.1:6379> DECR k1 
(integer) 4
# 将k1的值减3，返回1
127.0.0.1:6379> DECRBY k1 3
(integer) 1
# 将k1的值加2
127.0.0.1:6379> DECRBY k1 -2
(integer) 3
```

* INCRBYFLOAT
`INCRBYFLOAT`与上述四个命令有较为明显的区别，它会将字符串解析为双精度浮点数（double类型），且递增的值接受浮点数。同样，若键不存在则会将其值设置为0，若键值对不为字符串类型或无法解析为双精度浮点数则会返回错误。
`INCRBYFLOAT`命令执行后会将计算得到的结果保存至键值对中并返回对应的值，返回的值为字符串值而非上述命令一样的整型值。
`INCRBYFLOAT key increment`
使用`INCRBYFLOAT`命令时，原字符串的值及递增的值都可包含指数，但在计算后将被保存为小数的形式。在计算后，小数点后多余的0将被删除。浮点数的计算会存在精度的问题，计算的结果最多只保留小数点后的17位。

* 示例
```
127.0.0.1:6379> SET k4 100
OK
127.0.0.1:6379> INCRBYFLOAT k4 50.5
"150.5"
127.0.0.1:6379> INCRBYFLOAT k4 100.3
"250.8"

# 当值为指数时，使用INCRBYFLOAT命令后将被转换为小数的形式：
127.0.0.1:6379> SET k5 314e-2
OK
127.0.0.1:6379> INCRBYFLOAT k5 0.0
"3.14"
127.0.0.1:6379> GET k5
"3.14"
```

##### 5.1.7 APPEND
* APPEND
`APPEND`命令的作用为当指定键存在且为字符串类型时，将指定的值拼接到现有值的最后。若指定键不存在时，其作用类似于使用`SET`命令，即创建一个空串并拼接参数指定的字符串。
`APPEND key value`

* 示例
```
127.0.0.1:6379> FLUSHALL
OK
127.0.0.1:6379> SET k1 hello
OK
127.0.0.1:6379> APPEND k1  world
(integer) 10
127.0.0.1:6379> GET k1
"helloworld"
```

##### 5.1.8 STRALGO
* STRALGO
`STRALGO`命令是6.0版本新增加的命令，用于执行一些复杂的字符串操作算法。
在当前最新版本中（6.0.5），仅支持了LCS算法（longest common substring，最长公共子串），其使用方式为：
```STRALGO LCS [KEYS ...] [STRINGS ...] [LEN] [IDX] [MINMATCHLEN <len>] [WITHMATCHLEN]```

* 示例
```
127.0.0.1:6379> STRALGO LCS STRINGS  helloworld helloredis
"hellord"
```
##### 5.1.9 SETBIT / BITCOUNT / BITOP / BITOPS / BITFIELD
* SETBIT
`SETBIT`命令用于设置指定偏移位的二进制值，设置的值必须为0或1。
```SETBIT key offset value```
使用`SETBIT`命令时，偏移量的值必须大于等于0，且小于4294967296（2^32）。若偏移量大于原字符串的长度，则该字符串将增长至能存放偏移量的长度，增长的部分将使用0填充。

* GETBIT
`GETBIT`命令用于返回指定指定偏移量的二进制值。
`GETBIT key offset`
使用`GETBIT`命令时若指定的偏移量大于字符串的长度，将认定超出的部分为连续的0。当键值对不存在时，将认定其为一个空白的字符串，即偏移量大于字符串的长度。与`SETBIT`命令不同的是，偏移量超出字符串时不会使字符串增长。

* BITCOUNT
`BITCOUNT`命令用于获取指定范围内字符串中二进制值为`1`的位数。默认情况下计数范围为整个字符串，另外也可手动指定计数开始和结束的位置。不同于上文中介绍的`SETBIT`以及`GETBIT`命令，`BITCOUNT`参数中的偏移量单位为字节而非位。
```BITCOUNT key [start end]```
与前文中介绍过的`GETRANGE`命令相似，也可以使用负数表示相对于字符串末尾的位置。

* 示例
```
127.0.0.1:6379> SETBIT k1 1 1
(integer) 0
127.0.0.1:6379> SETBIT k1 10 1
(integer) 0
127.0.0.1:6379> GET k1
"@ "
127.0.0.1:6379> GETBIT k1 1
(integer) 1
127.0.0.1:6379> GETBIT k1 10
(integer) 1
127.0.0.1:6379> GETBIT k1 2
(integer) 0
127.0.0.1:6379> GETBIT k1 1000
(integer) 0
127.0.0.1:6379> BITCOUNT k1
(integer) 2
127.0.0.1:6379> BITCOUNT k1 0 1
(integer) 2
```

* BITPOS
`BITPOS`命令用于获取在指定范围中首个二进制值为0或1的位置。当键值对不存在时，将认为是一个空字符串进行搜索。与`BITCOUNT`命令相同，`BITPOS`命令也可以以字节为单位指定偏移量，使用负数值表示相对于字符串末尾的位置。
`BITPOS key bit [start] [end]`
使用`BITPOS`命令时，将认定超出字符串长度的部分为连续的0。

* 返回值
`BITPOS`命令将返回首个符合条件的二进制值的位置。如当字符串值为`"\xfd"`时（即二进制值11111101），使用命令`BITPOS key 1`将得到结果为0，使用命令`BITPOS key 0`将得到结果为6。
当查找的值为二进制值1，且字符串为空或不包含1时，将返回-1。
当查找的值为二进制值0，字符串不为空且只包含有1时，将返回字符串后的第一个位的位置。如当字符串值为`"\xff"`时（即二进制值11111111），使用`BITPOS key 0`命令得到的结果为8。
当同时设置偏移量`start`以及`end`时，将只会在指定范围内查找。如当字符串值为`"\xff"`时（即二进制值11111111），使用`BITPOS key 0 0 0`命令得到的结果为-1。

* 示例
```
# 设置k1的值为 11111111 11111101
127.0.0.1:6379> SET k1 "\xff\xfd"
OK
# 返回1的个数，15
127.0.0.1:6379> BITCOUNT k1
(integer) 15
# 返回首个1的位置
127.0.0.1:6379> BITPOS k1 1 0 -1
(integer) 0
# 返回首个0的位置
127.0.0.1:6379> BITPOS k1 0 0 -1
(integer) 14
# 在第1个字节内，返回首个0出现的位置，返回-1
127.0.0.1:6379> BITPOS k1 0 0 0 
(integer) -1
```

* BITOP
`BITOP`命令用于对多个值（除`NOT`操作外）执行位运算操作，并将结果保存至指定的键值对中。`BITOP`命令将返回结果字符串的长度，其值等于输入中最长字符串的长度。
`BITOP operation destkey key [key ...]`
`BITOP`命令支持与（`AND`)、或（`OR`）、异或（`XOR`）以及非（`NOT`）四个位运算操作，其使用方式为：
`AND` 与操作，使用方式为`BITOP AND destkey srckey1 srckey2 ...`
`OR` 或操作，使用方式为`BITOP OR destkey srckey1 srckey2 ...`
`XOR` 异或操作，使用方式为`BITOP XOR destkey srckey1 srckey2 ...`
`NOT` 非操作，使用方式为`BITOP NOT destkey srckey`
当输入的字符串长度不同时，将使用0填充至与最长长度相同。若输入的键不存在则认定为一个空白字符串，并以0填充至与最长长度相同。

* 示例
```
# 10101010
127.0.0.1:6379> SET k1 "\xaa"
OK
# 01010101
127.0.0.1:6379> SET k2 "\x55"
OK
127.0.0.1:6379> BITOP AND k3 k1 k2
(integer) 1
# AND: 10101010 & 01010101 = 00000000
127.0.0.1:6379> GET k3
"\x00"
# OR: 10101010 | 01010101 = 11111111
127.0.0.1:6379> BITOP OR k4 k1 k2
(integer) 1
127.0.0.1:6379> GET k4
"\xff"
# XOR: 10101010 ^ 01010101 = 11111111
127.0.0.1:6379> BITOP XOR k5 k1 k2
(integer) 1
127.0.0.1:6379> GET k5
"\xff"
# NOT:  !10101010 = 01010101
127.0.0.1:6379> BITOP NOT k6 k1
(integer) 1
# 字符U二进制值为 01010101
127.0.0.1:6379> GET k6
"U"
# 不同长度的字符串进行位运算：
# key1的值将以0填充为 10101010 00000000
# 10101010 00000000 | 01010101 01010101 = 11111111 01010101
127.0.0.1:6379> BITOP OR k8 k1 k7
(integer) 3
127.0.0.1:6379> GET k8
"\xff55"
```

* BITFIELD
使用`BITFIELD`将字符串看成二进制位数组，并对其中存储不同长度的整数进行操作。例如设置偏移量为1234的5位有符号整数的值，或是获取偏移量为4567的31位无符号整数的值。同时，`BITFIELD`命令也提供了`INCRBY`子命令对值进行加/减操作，并提供了设置以处理溢出的情况。
```BITFIELD k1 [GET type offset] [SET type offset value] [INCRBY type offset increment] [OVERFLOW WRAP|SAT|FAIL]```
`BITFIELD`命令可通过传递多个命令对多个位域进行操作，并以数组的形式返回各命令执行的结果。例如下面的示例中，对`k1`键中偏移量为100的5位有符号整数进行自增量为1的自增操作，并获取偏移量为0的4位无符号整数的值：
```
redis> BITFIELD k1 INCRBY i5 100 1 GET u4 0
1) (integer) 1
2) (integer) 0
```
`BITFIELD`命令提供了下列支持的子命令：
`GET <type> <offset>` 返回指定位域的值，若超出字符串的长度，超出部分将为0。
`SET <type> <offset> <value>` 设置指定位域的值，并返回旧值。若超出字符串的长度，超出部分将以0填充。
`INCRBY <type> <offset> <increment>` 对指定位域的值进行自增操作（通过使用负的自增值进行减法的操作），并返回最新的值。若超出字符串的长度，超出部分将以0填充。

* 示例
```
# 0111 1111
127.0.0.1:6379> BITFIELD k1 SET i8 0 127
1) (integer) 0
127.0.0.1:6379> GET k1
"\x7f"
# 11111 有符号为 -1
127.0.0.1:6379> BITFIELD k1 GET i5 1
1) (integer) -1
# 11111 无符号为 31
127.0.0.1:6379> BITFIELD k1 GET u5 1
1) (integer) 31

127.0.0.1:6379> BITFIELD k1 SET i8 0 120
1) (integer) 0
# 使用FAIL使发生溢出时不执行操作
127.0.0.1:6379> BITFIELD k1 OVERFLOW FAIL INCRBY i8 0 10
1) (nil)
# 使用SAT使发生溢出时保持在边界值
127.0.0.1:6379> BITFIELD k1 OVERFLOW SAT INCRBY i8 0 10
1) (integer) 127
# 使用WRAP使发生溢出时值按环绕模式改变
127.0.0.1:6379> BITFIELD k1 OVERFLOW WRAP INCRBY i8 0 1
1) (integer) -128
```