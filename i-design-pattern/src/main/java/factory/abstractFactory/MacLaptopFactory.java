package factory.abstractFactory;

import factory.common.cpu.Cpu;
import factory.common.cpu.IntelCpu;
import factory.common.memory.KingstonMemory;
import factory.common.memory.Memory;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 18:40
 */
public class MacLaptopFactory implements LaptopFactory {

    @Override
    public Cpu getCpuCreator() {
        return new IntelCpu();
    }

    @Override
    public Memory getMemoryCreator() {
        return new KingstonMemory();
    }
}
