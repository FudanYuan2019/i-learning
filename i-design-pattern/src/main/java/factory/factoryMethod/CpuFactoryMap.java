package factory.factoryMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 16:26
 */
public class CpuFactoryMap {
    private static final Map<String, ICpuFactory> cachedCreatorFactories = new HashMap<>();
    static {
        cachedCreatorFactories.put("AMD", new AmdCpuFactory());
        cachedCreatorFactories.put("Intel", new IntelCpuFactory());
        cachedCreatorFactories.put("IBM", new IbmCpuFactory());
    }

    public static ICpuFactory getCpuCreatorFactory(String brand) {
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("brand can not be empty");
        }

        if (cachedCreatorFactories.containsKey(brand)) {
            return cachedCreatorFactories.get(brand);
        }

        throw new IllegalStateException(String.format("The brand of %s does not exist", brand));
    }
}
