package iterator;

/**
 * @Author: Jeremy
 * @Date: 2020/9/2 22:46
 */
public class ArrayIterator<E> implements Iterator<E> {
    private int cursor;
    private ArrayList<E> arrayList;

    public ArrayIterator(ArrayList<E> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public boolean hasNext() {
        return cursor < arrayList.length();
    }

    @Override
    public void next() {
        cursor++;
    }

    @Override
    public E currentItem() {
        return arrayList.get(cursor);
    }
}
