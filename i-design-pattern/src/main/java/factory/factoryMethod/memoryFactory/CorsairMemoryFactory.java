package factory.factoryMethod.memoryFactory;

import factory.common.memory.CorsairMemory;
import factory.common.memory.Memory;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 18:44
 */
public class CorsairMemoryFactory implements IMemoryFactory {
    @Override
    public Memory getMemoryCreator() {
        return new CorsairMemory();
    }
}
