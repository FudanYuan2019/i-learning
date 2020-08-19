package factory.common.memory;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 17:00
 */
public abstract class Memory {
    private String brand;
    private String useTech;

    public Memory(String brand, String useTech) {
        this.brand = brand;
        this.useTech = useTech;
    }

    public abstract void create();
}
