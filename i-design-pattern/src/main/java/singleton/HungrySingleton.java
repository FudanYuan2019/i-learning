package singleton;

/**
 * @Author: Jeremy
 * @Date: 2020/8/18 16:39
 */
public class HungrySingleton {
    private String content;
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
        content = "This is a singleton object from hungry type";
    }

    public static HungrySingleton getInstance() {
        return instance;
    }

    public String getContent() {
        return content;
    }
}
