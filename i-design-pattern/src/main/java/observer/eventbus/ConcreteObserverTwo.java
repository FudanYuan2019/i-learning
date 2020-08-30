package observer.eventbus;

import com.google.common.eventbus.Subscribe;
import observer.common.Observer;

/**
 * @Author: Jeremy
 * @Date: 2020/8/30 10:51
 */
public class ConcreteObserverTwo implements Observer {

    @Override
    @Subscribe
    public void update(String message) {
        System.out.println(String.format("observer two receive message %s", message));
        // ... Omitting process logic
    }
}
