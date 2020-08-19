package factory.simpleFactory;

import factory.common.cpu.AmdCpu;
import factory.common.cpu.Cpu;
import factory.common.cpu.IbmCpu;
import factory.common.cpu.IntelCpu;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 16:26
 */
public class CpuFactory {
    public static Cpu getCpuCreator(String brand) {
        Cpu cpu = null;
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("brand can not be empty");
        }

        if ("AMD".equals(brand)) {
            cpu = new AmdCpu();
        } else if ("Intel".equals(brand)) {
            cpu = new IntelCpu();
        } else if ("IBM".equals(brand)) {
            cpu = new IbmCpu();
        } else {
            throw new IllegalStateException(String.format("The brand of %s does not exist", brand));
        }

        return cpu;
    }
}
