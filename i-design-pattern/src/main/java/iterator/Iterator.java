package iterator;

/**
 * @Author: Jeremy
 * @Date: 2020/9/2 22:43
 */
public interface Iterator<E> {
    boolean hasNext();
    void next();
    E currentItem();
}
