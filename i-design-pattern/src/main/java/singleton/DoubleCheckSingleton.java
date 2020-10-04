package singleton;

/**
 * @Author: Jeremy
 * @Date: 2020/8/18 16:45
 */
public class DoubleCheckSingleton {
    private String content;
    private static volatile DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {
        content = "This is a singleton object from double-check type";
    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

    public String getContent() {
        return content;
    }
}
