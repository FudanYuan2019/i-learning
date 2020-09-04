package state;

/**
 * @Author: Jeremy
 * @Date: 2020/9/4 23:05
 */
public class SuperMario implements IMario {
    private MarioStateMachine marioStateMachine;

    public SuperMario(MarioStateMachine marioStateMachine) {
        this.marioStateMachine = marioStateMachine;
    }

    @Override
    public State getState() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom() {
        System.out.println("Super Mario obtain mush room");
        this.marioStateMachine.addMoney(100);
        System.out.println("add money: " + 100);
    }

    @Override
    public void meetMonster() {
        System.out.println("Super Mario meets monster");
        this.marioStateMachine.setState(new SmallMario(marioStateMachine));
        this.marioStateMachine.subMoney(200);
        System.out.println("sub money: " + 200);
        System.out.println("current state: " + marioStateMachine.getState().getState());
    }
}
