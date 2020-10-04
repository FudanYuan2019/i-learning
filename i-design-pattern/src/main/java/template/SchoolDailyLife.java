package template;

/**
 * @Author: Jeremy
 * @Date: 2020/8/30 21:57
 */
public class SchoolDailyLife extends AbstractDailyLife {
    @Override
    public void breakfast() {
        System.out.println("No breakfast");
    }

    @Override
    public void work() {
        System.out.println("Experiment & Write");
    }

    @Override
    public void lunch() {
        System.out.println("Normal lunch when I'am hungry.");
    }

    @Override
    public void dinner() {
        System.out.println("Normal dinner when I'am hungry.");
    }

    @Override
    public void sleep() {
        System.out.println("I can persist, continue experimenting and writing");
    }
}
