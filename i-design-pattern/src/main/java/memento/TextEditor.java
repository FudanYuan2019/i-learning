package memento;

/**
 * @Author: Jeremy
 * @Date: 2020/9/7 21:19
 */
public class TextEditor {
    private StringBuilder editor;

    public TextEditor() {
        editor = new StringBuilder();
    }

    public String getText() {
        return editor.toString();
    }

    public void append(String text) {
        this.editor.append(text);
    }

    public Snapshot createSnapshot() {
        return new Snapshot(this.getText());
    }

    public void restoreSnapshot(Snapshot snapshot) {
        this.editor.replace(0, this.editor.length(), snapshot.getText());
    }
}
