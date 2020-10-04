package singleton;

/**
 * @Author: Jeremy
 * @Date: 2020/8/18 16:44
 */
public class LazySingleton {
    private String content;
    private static LazySingleton instance;

    private LazySingleton() {
        content = "This is a singleton object from lazy type";
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public String getContent() {
        return content;
    }
}
