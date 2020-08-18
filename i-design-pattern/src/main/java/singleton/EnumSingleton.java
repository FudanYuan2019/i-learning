package singleton;

/**
 * @Author: Jeremy
 * @Date: 2020/8/18 16:48
 */
public enum EnumSingleton {
    INSTANCE;
    private String content = "This is a singleton object from enum type";

    public String getContent() {
        return content;
    }
}
