package responsibility.handler;

/**
 * @Author: Jeremy
 * @Date: 2020/9/1 22:12
 */
public class StudentHandler extends Handler {

    @Override
    protected void doHandle(ScholarshipApplyForm form) {
        form.setStatus("The form has been fulfilled. Wait for mentor to sign.");
    }
}
