package observer.common;

/**
 * @Author: Jeremy
 * @Date: 2020/8/30 10:52
 */
public interface Subject {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(String message);
}
