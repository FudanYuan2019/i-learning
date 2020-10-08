/**
 * @Author: Jeremy
 * @Date: 2020/10/7 21:52
 */
public class StaticTest {
    static {
        System.out.println("static 123");
    }

    public static void main(String[] args) {
        StaticTest.staticFunc();

        StaticTest staticTest = new StaticTest();
        staticTest.nonStaticFunc();

        StaticTest.InnerClass innerClass = new InnerClass();
        innerClass.test();
    }

    public static class InnerClass {
        public void test() {
            System.out.println("test");
        }
    }

    public static void staticFunc() {
        System.out.println("俺是一个类方法");
    }

    public void nonStaticFunc() {
        System.out.println("俺是一个成员方法");
    }
}
