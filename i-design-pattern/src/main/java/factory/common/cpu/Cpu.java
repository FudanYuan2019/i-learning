package factory.common.cpu;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 16:27
 */
public abstract class Cpu {
    private String brand;
    private String useTech;

    public Cpu(String brand, String useTech) {
        this.brand = brand;
        this.useTech = useTech;
    }

    public abstract void create();
}
