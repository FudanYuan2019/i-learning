package singleton;

/**
 * @Author: Jeremy
 * @Date: 2020/8/18 16:47
 */
public class StaticInnerClassSingleton {
    private String content;

    private StaticInnerClassSingleton() {
        content = "This is a singleton object from static inner class type";
    }

    public String getContent() {
        return content;
    }

    private static class StaticInnerClassSingletonHolder {
        private static final StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return StaticInnerClassSingletonHolder.staticInnerClassSingleton;
    }
}
