package state;

/**
 * @Author: Jeremy
 * @Date: 2020/9/4 23:04
 */
public class MarioStateMachine {
    private Integer money;
    private IMario state;

    public Integer getMoney() {
        return money;
    }

    public IMario getState() {
        return state;
    }

    public void setState(IMario state) {
        this.state = state;
    }

    public MarioStateMachine() {
        this.money = 0;
        this.state = new SmallMario(this);
    }

    public void addMoney(Integer prize) {
        this.money += prize;
    }

    public void subMoney(Integer punishment) {
        this.money -= punishment;
    }
}
