package state;

/**
 * @Author: Jeremy
 * @Date: 2020/9/4 22:59
 */
public enum State {
    INIT(-1, "init"),
    SMALL(0, "small"),
    SUPER(1, "super");

    State(Integer id, String state) {
        this.id = id;
        this.state = state;
    }

    private Integer id;
    private String state;
}
