package template;

/**
 * @Author: Jeremy
 * @Date: 2020/8/30 21:54
 */
public abstract class AbstractDailyLife {
    public void getUp() {
        System.out.println("get up and wash");
    }

    public abstract void breakfast();

    public abstract void work();

    public abstract void lunch();

    public abstract void dinner();

    public void sleep() {
        System.out.println("Go to sleep");
    }

    public final void live() {
        System.out.println("The new day begins");
        getUp();
        breakfast();
        work();
        lunch();
        work();
        dinner();
        work();
        sleep();
        System.out.println("The day has gone\n");
    }
}
