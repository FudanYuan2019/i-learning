package factory.common;

import factory.abstractFactory.LaptopFactory;
import factory.abstractFactory.LaptopFactoryMap;
import factory.common.cpu.Cpu;
import factory.common.memory.Memory;
import factory.factoryMethod.CpuFactoryMap;
import factory.factoryMethod.ICpuFactory;
import factory.simpleFactory.CpuFactoryV2;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 16:27
 */
public class Laptop {
    private Cpu cpu;
    private Memory memory;

    public Laptop() {

    }

    public void createBySimpleFactory(String cpuBrand, String memoryBrand) {
        System.out.println("------------ simple factory ------------");
        System.out.println("Laptop is creating");

        cpu = CpuFactoryV2.getCpuCreator(cpuBrand);
        cpu.create();

        System.out.println("Laptop is created finished");
        System.out.println();
    }

    public void createByFactoryMethod(String cpuBrand, String memoryBrand) {
        System.out.println("------------ factory method ------------");
        System.out.println("Laptop is creating");

        ICpuFactory cpuCreatorFactory = CpuFactoryMap.getCpuCreatorFactory(cpuBrand);
        cpuCreatorFactory.getCpuCreator().create();

        System.out.println("Laptop is created finished");
        System.out.println();
    }

    public void createByAbstractFactory(String laptopBrand) {
        System.out.println("------------ abstract factory ------------");
        System.out.println(String.format("%s Laptop is creating", laptopBrand));

        LaptopFactory laptopFactory = LaptopFactoryMap.getLaptopFactory(laptopBrand);
        laptopFactory.getCpuCreator().create();
        laptopFactory.getMemoryCreator().create();

        System.out.println(String.format("%s Laptop is created finished", laptopBrand));
        System.out.println();
    }
}
