package mapper;

import domain.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Jeremy
 * @Date: 2020/10/2 00:34
 */
@Mapper
public interface StudentMapper {
    /**
     * 插入学生对象
     *
     * @param student
     * @return
     */
    Long insert(Student student);

    /**
     * 查找学生对象
     *
     * @param id
     * @return
     */
    Student find(Long id);
}
