package utils;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

/**
 * @Author: Jeremy
 * @Date: 2020/10/2 01:14
 */
public class MybatisUtilStudentTest {
    /**
     * 测试连接
     */
    @Test
    public void testConnection() {
        Connection conn = MybatisUtil.getSqlSession().getConnection();
        Assert.assertNotNull(conn);
    }
}
