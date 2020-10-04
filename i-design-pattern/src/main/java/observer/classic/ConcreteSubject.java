package observer.classic;

import observer.common.Observer;
import observer.common.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/8/30 10:53
 */
public class ConcreteSubject implements Subject {

    private Boolean state;
    private List<Observer> observers = new ArrayList<>();

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public void addObserver(Observer observer) {
        if (this.observers.contains(observer)) {
            return;
        }
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!this.observers.contains(observer)) {
            return;
        }
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        if (getState()) {
            for (Observer observer : observers) {
                observer.update(message);
            }
        }
        setState(false);
    }
}
