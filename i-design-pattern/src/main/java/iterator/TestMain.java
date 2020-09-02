package iterator;

/**
 * @Author: Jeremy
 * @Date: 2020/9/2 22:57
 */
public class TestMain {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }

        list.remove(0);
        iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }
}
