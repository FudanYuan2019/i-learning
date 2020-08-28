package flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/8/28 21:17
 */
public class TestMain {
    public static void main(String[] args) {
        List<String> users1 = new ArrayList<>();
        users1.add("Jeremy");
        users1.add("Wei");
        users1.add("Min");

        Room room1 = new Room("1", users1);
        room1.dispatch();
        room1.info();

        List<String> users2 = new ArrayList<>();
        users2.add("Tom");
        users2.add("Sam");
        users2.add("Bill");

        Room room2 = new Room("2", users2);
        room2.dispatch();
        room2.info();
    }
}
