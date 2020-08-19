package factory.common.cpu;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 16:35
 */
public class IntelCpu extends Cpu {
    public IntelCpu() {
        super("Intel", "Intel use intel's unique technology");
        // Omitting complex initialization logic
        // ...
    }


    @Override
    public void create() {
        System.out.println("Intel CPU is creating");

        // Omitting complex creating logic
        // ...

        System.out.println("Intel CPU is created finished");
    }
}
