package factory;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 17:05
 */
public class TestMain {
    public static void main(String[] args) {
        Laptop laptop = new Laptop();
        laptop.createBySimpleFactory("Intel", "Kingston");

        laptop.createByFactoryMethod("IBM", "Kingston");

        laptop.createByAbstractFactory("Mac");
        laptop.createByAbstractFactory("Windows");
    }
}
