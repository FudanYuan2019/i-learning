package factory.abstractFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 18:59
 */
public class LaptopFactoryMap {
    private static final Map<String, LaptopFactory> cachedLaptopFactories = new HashMap<>();
    static {
        cachedLaptopFactories.put("Mac", new MacLaptopFactory());
        cachedLaptopFactories.put("Windows", new WindowsLaptopFactory());
    }

    public static LaptopFactory getLaptopFactory(String laptopBrand){
        if (laptopBrand == null || laptopBrand.isEmpty()) {
            throw new IllegalArgumentException("brand can not be empty");
        }

        if (cachedLaptopFactories.containsKey(laptopBrand)) {
            return cachedLaptopFactories.get(laptopBrand);
        }

        throw new IllegalStateException(String.format("The laptopBrand of %s does not exist", laptopBrand));
    }
}
