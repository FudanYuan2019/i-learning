package observer;

/**
 * @Author: Jeremy
 * @Date: 2020/8/30 10:51
 */
public class ConcreteObserverOne implements Observer {

    @Override
    public void update(String message) {
        System.out.println(String.format("observer one receive message %s", message));
        // ... Omitting process logic
    }
}
