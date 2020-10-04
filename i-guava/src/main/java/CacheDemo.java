import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jeremy
 * @Date: 2020/8/24 14:25
 */
public class CacheDemo {
    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .build();
        cache.put("key1", "value1");

        String value = cache.getIfPresent("key1");
        System.out.println(value);

        Thread.sleep(6000);
        value = cache.getIfPresent("key1");
        System.out.println(value);
    }
}
