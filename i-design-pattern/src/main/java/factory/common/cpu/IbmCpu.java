package factory.common.cpu;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 16:34
 */
public class IbmCpu extends Cpu {

    public IbmCpu() {
        super("IBM", "IBM use IBM's unique technology");
        // Omitting complex initialization logic
        // ...
    }

    @Override
    public void create() {
        System.out.println("IBM CPU is creating");

        // Omitting complex creating logic
        // ...

        System.out.println("IBM CPU is created finished");
    }
}
