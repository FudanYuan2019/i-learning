package responsibility.handler;

/**
 * @Author: Jeremy
 * @Date: 2020/9/1 22:12
 */
public class UniversityHandler extends Handler {

    @Override
    protected void doHandle(ScholarshipApplyForm form) {
        form.setUniversitySuggestion("Approve");
        form.setStatus("The university has approved. Award scholarships.");
    }
}
