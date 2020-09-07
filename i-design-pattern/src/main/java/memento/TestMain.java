package memento;

import java.util.Scanner;

/**
 * @Author: Jeremy
 * @Date: 2020/9/7 21:24
 */
public class TestMain {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        SnapshotHolder snapshotHolder = new SnapshotHolder();
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            String text = scanner.next();
            if (":list".equals(text)) {
                System.out.println(textEditor.getText());
            } else if (":undo".equals(text)) {
                Snapshot snapshot = snapshotHolder.pop();
                textEditor.restoreSnapshot(snapshot);
            } else {
                Snapshot snapshot = textEditor.createSnapshot();
                snapshotHolder.push(snapshot);
                textEditor.append(text);
            }
        }
    }
}
