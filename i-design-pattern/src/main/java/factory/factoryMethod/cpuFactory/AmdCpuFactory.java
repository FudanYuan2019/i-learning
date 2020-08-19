package factory.factoryMethod.cpuFactory;

import factory.common.cpu.AmdCpu;
import factory.common.cpu.Cpu;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 18:04
 */
public class AmdCpuFactory implements ICpuFactory {
    @Override
    public Cpu getCpuCreator() {
        return new AmdCpu();
    }
}
