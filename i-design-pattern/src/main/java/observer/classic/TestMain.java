package observer.classic;

import observer.common.Observer;

/**
 * @Author: Jeremy
 * @Date: 2020/8/30 10:56
 */
public class TestMain {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserverOne();
        Observer observer2 = new ConcreteObserverTwo();
        subject.addObserver(observer1);
        subject.addObserver(observer2);

        // change subject's state
        subject.setState(true);
        subject.notifyObservers("Update message 1");

        // remove observer1
        subject.removeObserver(observer1);
        subject.setState(true);
        subject.notifyObservers("Update message 2");
    }
}
