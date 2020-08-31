package template;

/**
 * @Author: Jeremy
 * @Date: 2020/8/30 21:57
 */
public class CompanyDailyLife extends AbstractDailyLife {
    @Override
    public void breakfast() {
        System.out.println("Have healthy breakfast at about 9 AM.");
    }

    @Override
    public void work() {
        System.out.println("Code & Find bugs & Fix bugs");
    }

    @Override
    public void lunch() {
        System.out.println("Have rich lunch at about 12 PM.");
    }

    @Override
    public void dinner() {
        System.out.println("Have delicious dinner at about 7 PM.");
    }

    @Override
    public void sleep() {
        System.out.println("Go to sleep at about 12 AM.");
    }
}
