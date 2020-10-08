/**
 * @Author: Jeremy
 * @Date: 2020/10/7 22:20
 */
public class ObjectTest {
    private String name;
    private int age;

    public ObjectTest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }

    public static void main(String[] args) {
        ObjectTest objectTest = new ObjectTest("Daidai", 24);
        System.out.println(objectTest);

        int res = 1;
        System.out.println(res);

        String s = "112";
        System.out.println(s);

        char[] ch = new char[]{'1', '2'};
        System.out.println(ch);

        int[] array = new int[]{1, 2};
        System.out.println(array);
    }
}
