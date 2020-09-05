package visitor;

/**
 * @Author: Jeremy
 * @Date: 2020/9/5 23:46
 */
public class MentorVisitor implements Visitor {
    @Override
    public void visit(AcademicMaster academicMaster) {
        System.out.println(String.format("name: %s, paper count: %d",
                academicMaster.getName(), academicMaster.getPaperCount()));
    }

    @Override
    public void visit(EngineerMaster engineerMaster) {
        System.out.println(String.format("name: %s, project count: %d",
                engineerMaster.getName(), engineerMaster.getProjectCount()));
    }
}
