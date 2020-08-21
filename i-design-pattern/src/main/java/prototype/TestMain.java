package prototype;

import java.io.IOException;

/**
 * @Author: Jeremy
 * @Date: 2020/8/21 16:47
 */
public class TestMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GoldenHoop goldenHoop = new GoldenHoop(6D, 135000D);
        MonkeyKing monkeyKing = new MonkeyKing(1.2D, 100D, goldenHoop);

        for (long i = 0; i < 3L; i++){
            MonkeyKing monkeySons = monkeyKing.clone(i);

            System.out.println(monkeySons);
            System.out.println(monkeySons.getGoldenHoop());
            System.out.println();
        }


        for (long i = 0; i < 3L; i++){
            MonkeyKing monkeySons = monkeyKing.deepClone(i);

            System.out.println(monkeySons);
            System.out.println(monkeySons.getGoldenHoop());
            System.out.println();
        }
    }
}
