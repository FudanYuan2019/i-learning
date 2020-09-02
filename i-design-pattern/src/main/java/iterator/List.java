package iterator;

/**
 * @Author: Jeremy
 * @Date: 2020/9/2 22:44
 */
public interface List<E> {
    Iterator<E> iterator();

    void add(E element);

    void remove(int index);

    E get(int index);

    int length();
}
