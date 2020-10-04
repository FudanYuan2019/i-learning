package memento;

/**
 * @Author: Jeremy
 * @Date: 2020/9/7 21:20
 */
public class Snapshot {
    private String text;

    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
