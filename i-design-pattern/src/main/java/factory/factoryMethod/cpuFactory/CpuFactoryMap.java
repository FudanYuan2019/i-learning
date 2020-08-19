package factory.factoryMethod.cpuFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 16:26
 */
public class CpuFactoryMap {
    private static final Map<String, ICpuFactory> cachedCreatorFactorys = new HashMap<>();
    static {
        cachedCreatorFactorys.put("AMD", new AmdCpuFactory());
        cachedCreatorFactorys.put("Intel", new IntelCpuFactory());
        cachedCreatorFactorys.put("IBM", new IbmCpuFactory());
    }

    public static ICpuFactory getCpuCreatorFactory(String brand) {
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("brand can not be empty");
        }

        if (cachedCreatorFactorys.containsKey(brand)) {
            return cachedCreatorFactorys.get(brand);
        }

        throw new IllegalStateException(String.format("The brand of %s does not exist", brand));
    }
}
