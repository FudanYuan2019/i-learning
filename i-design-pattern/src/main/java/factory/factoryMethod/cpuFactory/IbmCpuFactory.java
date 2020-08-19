package factory.factoryMethod.cpuFactory;

import factory.common.cpu.Cpu;
import factory.common.cpu.IbmCpu;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 18:04
 */
public class IbmCpuFactory implements ICpuFactory {
    @Override
    public Cpu getCpuCreator() {
        return new IbmCpu();
    }
}
