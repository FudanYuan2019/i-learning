package decorator;

/**
 * @Author: Jeremy
 * @Date: 2020/8/24 23:04
 */
public class MilkTeaWithPudding extends FilterMilkTea {

    public MilkTeaWithPudding(MilkTea milkTea) {
        super(milkTea);
    }

    @Override
    public void make() {
        this.milkTea.make();
        System.out.println("Add pudding");
    }

    @Override
    public Integer price() {
        return this.milkTea.price() + 3;
    }

}
