package state;

/**
 * @Author: Jeremy
 * @Date: 2020/9/4 23:03
 */
public class SmallMario implements IMario {
    private MarioStateMachine marioStateMachine;

    public SmallMario(MarioStateMachine marioStateMachine) {
        this.marioStateMachine = marioStateMachine;
    }

    @Override
    public State getState() {
        return State.SMALL;
    }

    @Override
    public void obtainMushRoom() {
        System.out.println("Small Mario obtain mushroom");
        marioStateMachine.addMoney(100);
        marioStateMachine.setState(new SuperMario(marioStateMachine));
        System.out.println("Add money: " + 100);
        System.out.println("Current state: " + marioStateMachine.getState().getState());
    }

    @Override
    public void meetMonster() {
        System.out.println("Small Mario meets monster");
        marioStateMachine.addMoney(200);
        System.out.println("Sub money: " + 200);
        System.out.println("Current state: " + marioStateMachine.getState().getState());
    }
}
