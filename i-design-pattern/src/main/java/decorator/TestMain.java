package decorator;

/**
 * @Author: Jeremy
 * @Date: 2020/8/24 23:06
 */
public class TestMain {
    public static void main(String[] args) {
        MilkTea milkTea = new OriginMilkTea();
        MilkTeaWithPearl milkTeaWithPearl = new MilkTeaWithPearl(milkTea);
        MilkTeaWithPudding milkTeaWithPudding = new MilkTeaWithPudding(milkTeaWithPearl);
        MilkTeaWithSugar milkTeaWithSugar = new MilkTeaWithSugar(milkTeaWithPudding);

        milkTeaWithSugar.make();
        System.out.println(String.format("It costs you %d RMB", milkTeaWithSugar.price()));
    }
}
