package responsibility.handler;

/**
 * @Author: Jeremy
 * @Date: 2020/9/1 22:09
 */
public abstract class Handler {
    protected Handler successor = null;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public final void handle(ScholarshipApplyForm form) {
        doHandle(form);
        System.out.println(form.getStatus());
        if (successor != null) {
            successor.handle(form);
        }
    }

    protected abstract void doHandle(ScholarshipApplyForm form);
}
