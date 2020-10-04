package factory.simpleFactory;

import factory.common.cpu.AmdCpu;
import factory.common.cpu.Cpu;
import factory.common.cpu.IbmCpu;
import factory.common.cpu.IntelCpu;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 16:26
 */
public class CpuFactoryV2 {
    private static final Map<String, Cpu> cachedCreators = new HashMap<>();
    static {
        cachedCreators.put("AMD", new AmdCpu());
        cachedCreators.put("Intel", new IntelCpu());
        cachedCreators.put("IBM", new IbmCpu());
    }
    public static Cpu getCpuCreator(String brand) {
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("brand can not be empty");
        }

        if (cachedCreators.containsKey(brand)) {
            return cachedCreators.get(brand);
        }

        throw new IllegalStateException(String.format("The brand of %s does not exist", brand));
    }
}
