package observer.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import observer.common.Observer;
import observer.common.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author: Jeremy
 * @Date: 2020/8/30 21:18
 */
public class ConcreteSubject implements Subject {

    private Boolean state;
    private List<Observer> observers = new ArrayList<>();

    private EventBus eventBus;
    private static final Integer EXECUTOR_POOL_SIZE = 10;
    private static final Executor executor = Executors.newFixedThreadPool(EXECUTOR_POOL_SIZE);

    public ConcreteSubject() {
        eventBus = new AsyncEventBus(executor);
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public void addObserver(Observer observer) {
        if (observers.contains(observer)) {
            return;
        }
        observers.add(observer);
        eventBus.register(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!observers.contains(observer)) {
            return;
        }
        eventBus.unregister(observer);
    }

    @Override
    public void notifyObservers(String message) {
        if (getState()) {
            eventBus.post(message);
        }
    }
}
