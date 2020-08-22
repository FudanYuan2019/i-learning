package proxy.controller;

import proxy.entity.User;

/**
 * @Author: Jeremy
 * @Date: 2020/8/22 18:48
 */
public interface UserController {
    boolean login(User user);
    boolean register(User user);
}
