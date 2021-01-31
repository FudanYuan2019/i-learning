import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.concurrent.*;

/**
 * @author yinzhensheng
 * @date 2021/1/31 5:18 下午
 */
public class TestMain {
    private static final ExecutorService PRODUCER_SERVICE = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), new ThreadFactoryBuilder()
            .setNameFormat("producer-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());

    private static final ExecutorService CONSUMER_SERVICE = new ThreadPoolExecutor(1, 5, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), new ThreadFactoryBuilder()
            .setNameFormat("consumer-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        final String servers = "192.168.1.110:32774,192.168.1.110:32775,192.168.1.110:32776";
        final String topic = "test";
        KafkaProducer<String, String> producer = KafkaUtils.createProducer(servers);
        PRODUCER_SERVICE.execute(() -> {
            while (true) {
                final String message = "This is a test message@" + System.currentTimeMillis();
                System.out.printf("sending message: %s%n", message);
                KafkaUtils.send(producer, topic, message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        KafkaConsumer<String, String> consumer = KafkaUtils.createConsumer(servers, topic);
        CONSUMER_SERVICE.execute(() -> KafkaUtils.readMessage(consumer, 100));

    }
}
