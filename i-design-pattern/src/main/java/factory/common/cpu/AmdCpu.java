package factory.common.cpu;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 16:34
 */
public class AmdCpu extends Cpu {

    public AmdCpu() {
        super("AMD", "AMD use AMD's unique technology");
        // Omitting complex initialization logic
        // ...
    }

    @Override
    public void create() {
        System.out.println("AMD CPU is creating");

        // Omitting complex creating logic
        // ...

        System.out.println("AMD CPU is created finished");
    }
}
