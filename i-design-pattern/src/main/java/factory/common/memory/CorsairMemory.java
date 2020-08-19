package factory.common.memory;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 17:10
 */
public class CorsairMemory extends Memory {
    public CorsairMemory() {
        super("Corsair", "Corsair use Corsair's unique technology");
        // Omitting complex initialization logic
        // ...
    }

    @Override
    public void create() {
        System.out.println("Corsair memory is creating");

        // Omitting complex creating logic
        // ...

        System.out.println("Corsair memory is created finished");
    }
}
