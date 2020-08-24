package decorator;

/**
 * @Author: Jeremy
 * @Date: 2020/8/24 23:24
 */
public class OriginMilkTea implements MilkTea{
    @Override
    public void make() {
        System.out.println("This is an origin milk tea.");
    }

    @Override
    public Integer price() {
        return 10;
    }
}
