import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author: Jeremy
 * @Date: 2020/10/5 13:04
 */
public class RedisClient {
    private RedisClient() {

    }

    private static class RedisClientHolder {
        private static final Jedis jedis = new Jedis("localhost");
    }

    public static Jedis createRedisClient() {
        return RedisClientHolder.jedis;
    }

    public static void main(String[] args) {
        Jedis jedis = RedisClient.createRedisClient();

        System.out.println("-------------------测试redis实例创建-----------------");
        System.out.println("connect successful!");
        System.out.println("service running: " + jedis.ping());

        System.out.println("-------------------测试字符串-----------------");
        jedis.set("k1", "v1");
        System.out.println("key k1 = " + jedis.get("k1"));

        System.out.println("-------------------测试List-----------------");
        jedis.lpush("l1", "item1");
        jedis.lpush("l1", "item2");
        jedis.lpush("l1", "item3");

        List<String> list = jedis.lrange("l1", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("item" + (i + 1) + " = " + list.get(i));
        }

        System.out.println("-------------------测试Map-----------------");
        jedis.hset("h1", "name", "Jeremy");
        jedis.hset("h1", "age", "24");
        String name = jedis.hget("h1", "name");
        String age = jedis.hget("h1", "age");
        System.out.println(String.format("name= %s, age = %s", name, age));
        List<String> values = jedis.hmget("h1", "name", "age");
        System.out.println(String.format("name= %s, age = %s", values.get(0), values.get(1)));

        System.out.println("-------------------测试Set-----------------");
        jedis.sadd("s1", "ooxx");
        jedis.sadd("s1", "xxoo");
        jedis.sadd("s1", "xoxo");
        jedis.sadd("s1", "xxoo");
        System.out.println(String.format("s1 共 %d 个不同元素", jedis.scard("s1")));
        Set<String> s1 = jedis.smembers("s1");
        for (String s : s1) {
            System.out.println(s);
        }
        while (jedis.scard("s1") > 0) {
            String s = jedis.spop("s1");
            System.out.println(String.format("s = %s", s));
        }

        System.out.println("----------------测试Sorted Set------------");
        jedis.zadd("fruits", 1.0, "apple");
        jedis.zadd("fruits", 2.0, "banana");
        jedis.zadd("fruits", 1.3, "orange");
        long count = jedis.zcard("fruits");
        System.out.println(String.format("共 %d 中水果", count));
        Set<String> sorted = jedis.zrangeByScore("fruits", 0, count - 1);
        for (String s : sorted) {
            System.out.println(jedis.zrank("fruits", s) + " " + s);
        }

        System.out.println("------------------测试keys *--------------");
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }
    }
}
