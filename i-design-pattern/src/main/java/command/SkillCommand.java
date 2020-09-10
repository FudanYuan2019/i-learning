package command;

/**
 * @Author: Jeremy
 * @Date: 2020/9/10 20:34
 */
public class SkillCommand implements Command {
    private Receiver receiver;

    public SkillCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
        System.out.println(String.format("use skill: %s", receiver.getSkill()));
    }
}
