package composite;

import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/8/27 21:37
 */
public class Department implements IEmployee{
    private String name;
    private List<IEmployee> employees;

    public Department(String name, List<IEmployee> employees) {
        this.name = name;
        this.employees = employees;
    }

    @Override
    public Integer count() {
        Integer count = 0;
        for (IEmployee employee : employees) {
            count += employee.count();
        }
        return count;
    }

    @Override
    public Integer getSalary() {
        Integer salary = 0;
        for (IEmployee employee : employees) {
            salary += employee.getSalary();
        }
        return salary;
    }
}
