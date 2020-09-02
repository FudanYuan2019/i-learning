package iterator;

/**
 * @Author: Jeremy
 * @Date: 2020/9/2 22:45
 */
public class ArrayList<E> implements List<E> {
    private Object[] array;
    private static final Integer DEFAULT_LENGTH = 10;
    private int len;

    public ArrayList() {
        array = new Object[DEFAULT_LENGTH];
        len = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(this);
    }

    @Override
    public void add(E element) {
        array[len++] = element;
    }

    @Override
    public void remove(int index) {
        int numMoved = len - index - 1;
        if (numMoved > 0)
            System.arraycopy(array, index + 1, array, index,
                    numMoved);
        array[--len] = null;
    }

    @Override
    public E get(int index) {
        return (E) array[index];
    }

    @Override
    public int length() {
        return this.len;
    }
}
