package responsibility.handler;

/**
 * @Author: Jeremy
 * @Date: 2020/9/1 22:12
 */
public class MentorHandler extends Handler {

    @Override
    protected void doHandle(ScholarshipApplyForm form) {
        form.setMentorSuggestion("Approve");
        form.setStatus("The mentor has signed. Wait for school to approve.");
    }
}
