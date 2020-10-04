package composite;

/**
 * @Author: Jeremy
 * @Date: 2020/8/27 21:33
 */
public class Employee implements IEmployee{
    private String name;
    private Integer age;
    private Integer salary;

    public Employee(String name, Integer age, Integer salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public Integer count() {
        return 1;
    }

    @Override
    public Integer getSalary() {
        return this.salary;
    }
}
