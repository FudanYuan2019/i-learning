package proxy.proxy.staticProxy;

import proxy.controller.UserController;
import proxy.entity.User;

/**
 * @Author: Jeremy
 * @Date: 2020/8/22 18:21
 */
public class UserControllerImplProxy implements UserController {
    private UserController userController;

    public UserControllerImplProxy(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean login(User user) {
        Long startTime = System.currentTimeMillis();
        boolean res = userController.login(user);
        Long endTime = System.currentTimeMillis();
        System.out.println(String.format("login elapsed %d ms", endTime - startTime));
        return res;
    }

    @Override
    public boolean register(User user) {
        Long startTime = System.currentTimeMillis();
        boolean res = userController.register(user);
        Long endTime = System.currentTimeMillis();
        System.out.println(String.format("register elapsed %d ms", endTime - startTime));
        return res;
    }
}
