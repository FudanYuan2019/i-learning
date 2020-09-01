package responsibility;

import responsibility.handler.Handler;
import responsibility.handler.ScholarshipApplyForm;

/**
 * @Author: Jeremy
 * @Date: 2020/9/1 22:28
 */
public class HandlerChain {
    private Handler head = null;
    private Handler tail = null;

    public void addHandler(Handler handler) {
        handler.setSuccessor(null);
        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }
        tail.setSuccessor(handler);
        tail = handler;
    }

    public void handle(ScholarshipApplyForm form) {
        if (head != null) {
            head.handle(form);
        }
    }
}
