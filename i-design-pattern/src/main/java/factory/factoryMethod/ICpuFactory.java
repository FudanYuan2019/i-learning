package factory.factoryMethod;

import factory.common.cpu.Cpu;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 18:03
 */
public interface ICpuFactory {
    Cpu getCpuCreator();
}
