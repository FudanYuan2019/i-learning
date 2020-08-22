package proxy;

import com.sun.tools.javac.util.Assert;
import proxy.controller.UserController;
import proxy.controller.impl.UserControllerImpl;
import proxy.entity.User;
import proxy.proxy.cglibDynamicProxy.CglibDynamicProxyFactory;
import proxy.proxy.jdkDynamicProxy.JdkDynamicProxyFactory;
import proxy.proxy.staticProxy.UserControllerImplProxy;

/**
 * @Author: Jeremy
 * @Date: 2020/8/22 17:52
 */
public class TestMain {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setAccount("test");
        user.setPassword("*****");

        UserController userController = new UserControllerImpl();

        // static proxy
        UserControllerImplProxy userControllerProxy1 = new UserControllerImplProxy(userController);
        Assert.check(userControllerProxy1.register(user), String.format("user [%s] registered error", user.toString()));
        Assert.check(userControllerProxy1.login(user), String.format("user [%s] login error", user.toString()));

        // jdk dynamic proxy
        JdkDynamicProxyFactory jdkDynamicProxyFactory = new JdkDynamicProxyFactory();
        UserController userControllerProxy2 = (UserController) jdkDynamicProxyFactory.createProxy(userController);
        userControllerProxy2.register(user);
        userControllerProxy2.login(user);

        // cglib proxy
        CglibDynamicProxyFactory cglibDynamicProxyFactory = new CglibDynamicProxyFactory(userController);
        UserController userControllerProxy3 = (UserController) cglibDynamicProxyFactory.getProxyInstance();
        userControllerProxy3.register(user);
        userControllerProxy3.login(user);
    }
}
