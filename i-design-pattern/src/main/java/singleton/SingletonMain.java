package singleton;

import com.sun.tools.javac.util.Assert;

/**
 * @Author: Jeremy
 * @Date: 2020/8/18 17:11
 */
public class SingletonMain {

    public static void main(String[] args) {
        // hungry
        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
        System.out.println(hungrySingleton.getContent());

        // lazy
        LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(lazySingleton.getContent());
        Assert.check(lazySingleton == LazySingleton.getInstance());

        // double-check
        DoubleCheckSingleton doubleCheckSingleton = DoubleCheckSingleton.getInstance();
        System.out.println(doubleCheckSingleton.getContent());
        Assert.check(doubleCheckSingleton == DoubleCheckSingleton.getInstance());


        // static inner classR
        StaticInnerClassSingleton staticInnerClassSingleton = StaticInnerClassSingleton.getInstance();
        System.out.println(staticInnerClassSingleton.getContent());
        Assert.check(staticInnerClassSingleton == StaticInnerClassSingleton.getInstance());

        // enum
        System.out.println(EnumSingleton.INSTANCE.getContent());
    }
}
