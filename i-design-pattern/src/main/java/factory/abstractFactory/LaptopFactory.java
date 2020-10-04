package factory.abstractFactory;

import factory.common.cpu.Cpu;
import factory.common.memory.Memory;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 18:37
 */
public interface LaptopFactory {
    Cpu getCpuCreator();
    Memory getMemoryCreator();
}
