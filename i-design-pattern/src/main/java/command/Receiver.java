package command;

/**
 * receiver
 *
 * @Author: Jeremy
 * @Date: 2020/9/10 20:46
 */
public class Receiver {
    private String name;
    private String skill;

    public Receiver(String name, String skill) {
        this.name = name;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public String getSkill() {
        return skill;
    }

    public void action() {
        System.out.println(String.format("%s do action", this.getName()));
    }
}
