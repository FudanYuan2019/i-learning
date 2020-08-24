package decorator;

/**
 * @Author: Jeremy
 * @Date: 2020/8/24 23:04
 */
public class MilkTeaWithSugar extends FilterMilkTea {

    public MilkTeaWithSugar(MilkTea milkTea) {
        super(milkTea);
    }

    @Override
    public void make() {
        this.milkTea.make();
        System.out.println("Add sugar");
    }
}
