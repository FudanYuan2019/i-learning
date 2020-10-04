package memento;

import java.util.Stack;

/**
 * @Author: Jeremy
 * @Date: 2020/9/7 21:21
 */
public class SnapshotHolder {
    private Stack<Snapshot> snapshots = new Stack<>();

    public Snapshot pop() {
        return snapshots.pop();
    }

    public void push(Snapshot snapshot) {
        snapshots.push(snapshot);
    }
}
