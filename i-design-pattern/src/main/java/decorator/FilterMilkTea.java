package decorator;

/**
 * @Author: Jeremy
 * @Date: 2020/8/24 22:59
 */
public class FilterMilkTea implements MilkTea {
    protected MilkTea milkTea;

    public FilterMilkTea(MilkTea milkTea) {
        this.milkTea = milkTea;
    }

    @Override
    public void make() {
        this.milkTea.make();
    }

    @Override
    public Integer price() {
        return this.milkTea.price();
    }
}
