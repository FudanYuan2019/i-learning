package visitor;

/**
 * @Author: Jeremy
 * @Date: 2020/9/5 23:44
 */
public class AcademicMaster extends Master {
    public AcademicMaster(String name, Double gpa, Double socialPractice, Integer paperCount, Integer projectCount) {
        super(name, gpa, socialPractice, paperCount, projectCount);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
