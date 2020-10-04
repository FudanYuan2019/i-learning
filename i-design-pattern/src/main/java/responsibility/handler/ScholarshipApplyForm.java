package responsibility.handler;

/**
 * @Author: Jeremy
 * @Date: 2020/9/1 22:14
 */
public class ScholarshipApplyForm {
    private String name;
    private String ID;
    private String studentId;
    private String applyCause;
    private String mentor;
    private String school;
    private String mentorSuggestion;
    private String schoolSuggestion;
    private String universitySuggestion;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getApplyCause() {
        return applyCause;
    }

    public void setApplyCause(String applyCause) {
        this.applyCause = applyCause;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMentorSuggestion() {
        return mentorSuggestion;
    }

    public void setMentorSuggestion(String mentorSuggestion) {
        this.mentorSuggestion = mentorSuggestion;
    }

    public String getSchoolSuggestion() {
        return schoolSuggestion;
    }

    public void setSchoolSuggestion(String schoolSuggestion) {
        this.schoolSuggestion = schoolSuggestion;
    }

    public String getUniversitySuggestion() {
        return universitySuggestion;
    }

    public void setUniversitySuggestion(String universitySuggestion) {
        this.universitySuggestion = universitySuggestion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
