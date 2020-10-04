package factory.factoryMethod;

import factory.common.cpu.Cpu;
import factory.common.cpu.IntelCpu;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 18:04
 */
public class IntelCpuFactory implements ICpuFactory {
    @Override
    public Cpu getCpuCreator() {
        return new IntelCpu();
    }
}
