package responsibility;

import responsibility.handler.*;

/**
 * @Author: Jeremy
 * @Date: 2020/9/1 22:31
 */
public class TestMain {
    public static void main(String[] args) {
        ScholarshipApplyForm form = new ScholarshipApplyForm();
        form.setName("Jeremy");
        form.setID("302911202009010011");
        form.setStudentId("20200901111");
        form.setApplyCause("I am so great!");
        form.setMentor("Tom");
        form.setSchool("Software School");

        HandlerChain chain = new HandlerChain();
        Handler studentHandler = new StudentHandler();
        Handler mentorHandler = new MentorHandler();
        Handler schoolHandler = new SchoolHandler();
        Handler universityHandler = new UniversityHandler();
        chain.addHandler(studentHandler);
        chain.addHandler(mentorHandler);
        chain.addHandler(schoolHandler);
        chain.addHandler(universityHandler);

        chain.handle(form);
    }
}
