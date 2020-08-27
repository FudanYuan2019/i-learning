package composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/8/27 21:42
 */
public class TestMain {
    public static void main(String[] args) {
        // the department struct is as follows:
        // Data
        // |__DataMining
        // |____Jeremy
        // |____Bob
        // |____Sam
        // |__MachineLearning
        // |____Bill
        // |____Tom
        // |____Amy
        IEmployee employee1 = new Employee("Jeremy", 20, 40000);
        IEmployee employee2 = new Employee("Bob", 21, 30000);
        IEmployee employee3 = new Employee("Sam", 20, 22000);
        List<IEmployee> subDepartment1Employees = new ArrayList<>();
        subDepartment1Employees.add(employee1);
        subDepartment1Employees.add(employee2);
        subDepartment1Employees.add(employee3);
        IEmployee subDepartment1 = new Department("DataMining", subDepartment1Employees);
        System.out.println(String.format("The DataMining subDepartment contains %d employees in total.", subDepartment1.count()));
        System.out.println(String.format("The DataMining subDepartment's salary is %d in total.", subDepartment1.getSalary()));

        IEmployee employee4 = new Employee("Bill", 24, 30000);
        IEmployee employee5 = new Employee("Tom", 30, 40000);
        IEmployee employee6 = new Employee("Amy", 24, 20000);
        List<IEmployee> subDepartment2Employees = new ArrayList<>();
        subDepartment2Employees.add(employee4);
        subDepartment2Employees.add(employee5);
        subDepartment2Employees.add(employee6);
        IEmployee subDepartment2 = new Department("MachineLearning", subDepartment2Employees);
        System.out.println(String.format("The MachineLearning subDepartment contains %d employees in total.", subDepartment2.count()));
        System.out.println(String.format("The MachineLearning subDepartment's salary is %d in total.", subDepartment2.getSalary()));

        List<IEmployee> subDepartments = new ArrayList<>();
        subDepartments.add(subDepartment1);
        subDepartments.add(subDepartment2);
        IEmployee department = new Department("Data", subDepartments);
        System.out.println(String.format("The Data department contains %d employees in total.", department.count()));
        System.out.println(String.format("The Data department's salary is %d in total.", department.getSalary()));
    }
}
