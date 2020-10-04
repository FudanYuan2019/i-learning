/**
 * @Author: Jeremy
 * @Date: 2020/8/22 22:01
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringApplicationMain {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath*:application.xml");
        Person person = context.getBean("person", Person.class);
        System.out.println(person.toString());
    }
}
