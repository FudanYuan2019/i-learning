package visitor;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/9/5 23:47
 */
public class TestMain {
    public static void main(String[] args) {
        Master academicMaster1 = new AcademicMaster("Jeremy", 3.7, 3.0, 1, 3);
        Master academicMaster2 = new AcademicMaster("Bob", 3.2, 2.0, 2, 1);
        Master engineerMaster1 = new EngineerMaster("Tom", 3.3, 4.0, 0, 1);
        Master engineerMaster2 = new EngineerMaster("Amy", 3.0, 3.0, 1, 2);
        List<Master> masters = new ArrayList<>();
        masters.add(academicMaster1);
        masters.add(academicMaster2);
        masters.add(engineerMaster1);
        masters.add(engineerMaster2);

        System.out.println("-----mentor's report-----");
        Visitor mentorVisitor = new MentorVisitor();
        for (Master master : masters) {
            master.accept(mentorVisitor);
        }

        System.out.println("-----counselor's report-----");
        Visitor counselorVisitor = new CounselorVisitor();
        for (Master master : masters) {
            master.accept(counselorVisitor);
        }
    }
}
