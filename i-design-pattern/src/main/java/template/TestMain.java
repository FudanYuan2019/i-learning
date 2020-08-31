package template;

/**
 * @Author: Jeremy
 * @Date: 2020/8/30 22:06
 */
public class TestMain {
    public static void main(String[] args) {
        // life in school
        AbstractDailyLife lifeInSchool = new SchoolDailyLife();
        lifeInSchool.live();

        // life in company
        AbstractDailyLife lifeInCompany = new CompanyDailyLife();
        lifeInCompany.live();
    }
}
