package state;

/**
 * @Author: Jeremy
 * @Date: 2020/9/4 22:58
 */
public interface IMario {
    State getState();

    void obtainMushRoom();

    void meetMonster();
}
