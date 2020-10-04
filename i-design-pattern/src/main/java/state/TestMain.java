package state;

/**
 * @Author: Jeremy
 * @Date: 2020/9/4 23:05
 */
public class TestMain {
    public static void main(String[] args) {
        MarioStateMachine marioStateMachine = new MarioStateMachine();
        marioStateMachine.getState().obtainMushRoom();
        marioStateMachine.getState().meetMonster();
        marioStateMachine.getState().meetMonster();
        Integer money = marioStateMachine.getMoney();
        System.out.println("money: " + money);
    }
}
