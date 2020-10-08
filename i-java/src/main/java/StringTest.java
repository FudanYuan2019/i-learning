/**
 * @Author: Jeremy
 * @Date: 2020/10/7 21:28
 */
public class StringTest {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("a");
        stringBuilder.append("b");
        String res = stringBuilder.toString();
        System.out.println(res);
    }
}
