package factory.abstractFactory;

import factory.common.cpu.AmdCpu;
import factory.common.cpu.Cpu;
import factory.common.memory.CorsairMemory;
import factory.common.memory.Memory;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 18:40
 */
public class WindowsLaptopFactory implements LaptopFactory {

    @Override
    public Cpu getCpuCreator() {
        return new AmdCpu();
    }

    @Override
    public Memory getMemoryCreator() {
        return new CorsairMemory();
    }
}
