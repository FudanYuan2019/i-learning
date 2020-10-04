package command;

/**
 * @Author: Jeremy
 * @Date: 2020/9/10 20:34
 */
public class MoveCommand implements Command {
    /**
     * target object
     */
    private Receiver receiver;

    /**
     * target loction
     */
    private double locX;
    private double locY;

    public MoveCommand(Receiver receiver, double locX, double locY) {
        this.receiver = receiver;
        this.locX = locX;
        this.locY = locY;
    }

    @Override
    public void execute() {
        this.receiver.action();
        System.out.println(String.format("Move to (%f, %f)", locX, locY));
    }
}
