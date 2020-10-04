package visitor;

/**
 * @Author: Jeremy
 * @Date: 2020/9/5 23:47
 */
public class CounselorVisitor implements Visitor {
    @Override
    public void visit(AcademicMaster academicMaster) {
        System.out.println(String.format("name: %s, gpa: %f",
                academicMaster.getName(), academicMaster.getGpa()));
    }

    @Override
    public void visit(EngineerMaster engineerMaster) {
        System.out.println(String.format("name: %s, social practice: %f",
                engineerMaster.getName(), engineerMaster.getSocialPractice()));
    }
}
