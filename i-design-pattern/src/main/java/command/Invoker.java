package command;

/**
 * @Author: Jeremy
 * @Date: 2020/9/10 20:46
 */
public class Invoker {
    /**
     * command obejct
     */
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    /**
     * action
     */
    public void action() {
        command.execute();
    }
}
