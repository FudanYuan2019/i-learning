package factory.common.memory;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 17:10
 */
public class SumsungMemory extends Memory {
    public SumsungMemory() {
        super("Sumsung", "Sumsung use Sumsung's unique technology");
        // Omitting complex initialization logic
        // ...
    }


    @Override
    public void create() {
        System.out.println("Sumsung memory is creating");

        // Omitting complex creating logic
        // ...

        System.out.println("Sumsung memory is created finished");
    }
}
