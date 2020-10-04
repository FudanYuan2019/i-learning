package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 学生对象
 *
 * @Author: Jeremy
 * @Date: 2020/10/2 00:08
 */
@Data
@AllArgsConstructor
@Alias("i_student")
public class Student {
    private Long id;

    private String name;

    private Short age;

    private Short sex;

    public Student(String name, Short age, Short sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return String.format("name: %s, age: %d, sex: %s", name, age, sex == 0 ? "男" : "女");
    }
}
