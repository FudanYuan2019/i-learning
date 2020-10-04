package responsibility.handler;

/**
 * @Author: Jeremy
 * @Date: 2020/9/1 22:12
 */
public class SchoolHandler extends Handler {

    @Override
    protected void doHandle(ScholarshipApplyForm form) {
        form.setSchoolSuggestion("Approve");
        form.setStatus("The school has approved. Wait for university to approve.");
    }
}
