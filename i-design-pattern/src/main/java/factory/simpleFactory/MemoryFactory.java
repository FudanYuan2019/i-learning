package factory.simpleFactory;

import factory.common.memory.CorsairMemory;
import factory.common.memory.KingstonMemory;
import factory.common.memory.Memory;
import factory.common.memory.SumsungMemory;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 17:17
 */
public class MemoryFactory {
    public static Memory getMemoryCreator(String brand) {
        Memory memory = null;
        if (brand == null) {
            throw new IllegalArgumentException("brand can not be empty");
        }

        if ("Kingston".equals(brand)) {
            memory = new KingstonMemory();
        } else if ("Corsair".equals(brand)) {
            memory = new CorsairMemory();
        } else if ("Sumsung".equals(brand)) {
            memory = new SumsungMemory();
        } else {
            throw new IllegalStateException(String.format("The brand of %s does not exist", brand));
        }

        return memory;
    }
}
