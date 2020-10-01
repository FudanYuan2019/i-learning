import domain.Student;
import mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import utils.MybatisUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/10/2 01:36
 */
public class TestMain {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student("Jeremy", (short) 24, (short) 0);
        long id = studentMapper.insert(student);
        System.out.println(id);

        Student res = studentMapper.find(id);
        System.out.println(res);

        sqlSession.commit();
        sqlSession.close();
    }
}
