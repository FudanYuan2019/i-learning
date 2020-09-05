package visitor;

/**
 * @Author: Jeremy
 * @Date: 2020/9/5 23:44
 */
public interface Visitor {
    void visit(AcademicMaster academicMaster);

    void visit(EngineerMaster engineerMaster);
}
