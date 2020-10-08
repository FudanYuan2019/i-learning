/**
 * @Author: Jeremy
 * @Date: 2020/10/7 23:37
 */
public class CloneExample implements Cloneable {
    private int a;
    private int b;
    private EqualsExample equalsExample;

    public CloneExample(int a, int b, EqualsExample equalsExample) {
        this.a = a;
        this.b = b;
        this.equalsExample = equalsExample;
    }

    @Override
    public CloneExample clone() throws CloneNotSupportedException {
        return new CloneExample(this.a, this.b, new EqualsExample(equalsExample.x, equalsExample.y, equalsExample.z));
    }

    public static void main(String[] args) {
        CloneExample e1 = new CloneExample(1, 2, new EqualsExample(1, 2, 3));
        try {
            System.out.println(e1.equalsExample);

            CloneExample e2 = e1.clone();

            System.out.println(e2.equalsExample);
            e1.equalsExample.x = 4;

            System.out.println(e1.equalsExample);

            System.out.println(e2.equalsExample);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
