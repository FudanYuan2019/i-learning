package factory.common.memory;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 17:10
 */
public class KingstonMemory extends Memory {
    public KingstonMemory() {
        super("Kingston", "Kingston use Kingston's unique technology");
        // Omitting complex initialization logic
        // ...
    }


    @Override
    public void create() {
        System.out.println("Kingston memory is creating");

        // Omitting complex creating logic
        // ...

        System.out.println("Kingston memory is created finished");
    }
}
