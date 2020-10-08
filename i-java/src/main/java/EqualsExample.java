import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: Jeremy
 * @Date: 2020/10/7 23:27
 */
public class EqualsExample {
    public int x;
    public int y;
    public int z;

    public EqualsExample(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", z=" + z;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        EqualsExample e1 = (EqualsExample) o;
        return e1.x == x && e1.y == y && e1.z == z;
    }

    public static void main(String[] args) throws Exception {
        HashSet<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        System.out.println(set1.size());

        EqualsExample e1 = new EqualsExample(1, 2, 3);
        EqualsExample e2 = new EqualsExample(3, 2, 1);
        HashSet<EqualsExample> set = new HashSet<>();
        set.add(e1);
        set.add(e2);
        System.out.println(set.size());

        HashMap<EqualsExample, Integer> map = new HashMap<>();
        try {
            map.put(e1, 1);
        } catch (Exception e) {
            throw e;
        }
    }
}
