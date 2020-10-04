package visitor;

/**
 * @Author: Jeremy
 * @Date: 2020/9/5 23:43
 */
public class EngineerMaster extends Master {
    public EngineerMaster(String name, Double gpa, Double socialPractice, Integer paperCount, Integer projectCount) {
        super(name, gpa, socialPractice, paperCount, projectCount);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
