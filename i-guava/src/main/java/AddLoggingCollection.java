import com.google.common.collect.ForwardingCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/8/24 14:53
 */
public class AddLoggingCollection<E> extends ForwardingCollection<E> {
    private Collection<E> collection;

    public AddLoggingCollection(Collection<E> collection) {
        this.collection = collection;
    }

    @Override
    protected Collection<E> delegate() {
        return collection;
    }

    public boolean add(E element) {
        System.out.println(String.format("add element %s", element));
        return delegate().add(element);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        AddLoggingCollection<String> addLoggingCollection = new AddLoggingCollection<>(list);
        addLoggingCollection.add("1");
        addLoggingCollection.remove("3");
    }
}
