package factory.factoryMethod.memoryFactory;

import factory.common.memory.Memory;
import factory.common.memory.SumsungMemory;

/**
 * @Author: Jeremy
 * @Date: 2020/8/19 18:44
 */
public class SumsungMemoryFactory implements IMemoryFactory {
    @Override
    public Memory getMemoryCreator() {
        return new SumsungMemory();
    }
}
