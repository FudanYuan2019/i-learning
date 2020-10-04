package visitor;

/**
 * @Author: Jeremy
 * @Date: 2020/9/5 23:40
 */
public abstract class Master {
    private String name;
    private Double gpa;
    private Double socialPractice;
    private Integer paperCount;
    private Integer projectCount;

    public Master(String name, Double gpa, Double socialPractice, Integer paperCount, Integer projectCount) {
        this.name = name;
        this.gpa = gpa;
        this.socialPractice = socialPractice;
        this.paperCount = paperCount;
        this.projectCount = projectCount;
    }

    public String getName() {
        return name;
    }

    public Double getGpa() {
        return gpa;
    }

    public Integer getPaperCount() {
        return paperCount;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public Double getSocialPractice() {
        return socialPractice;
    }

    public abstract void accept(Visitor visitor);
}
