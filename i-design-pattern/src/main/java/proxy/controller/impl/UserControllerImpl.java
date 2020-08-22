package proxy.controller.impl;

import proxy.controller.UserController;
import proxy.entity.User;

/**
 * @Author: Jeremy
 * @Date: 2020/8/22 17:50
 */
public class UserControllerImpl implements UserController {

    public boolean login(User user) {
        // Omitting login logic
        // ...
        return true;
    }

    public boolean register(User user) {
        try {
            // Omitting login logic
            // ...
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
