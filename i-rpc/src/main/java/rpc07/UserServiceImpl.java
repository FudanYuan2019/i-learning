package rpc07;

import common.User;
import common.UserService;

/**
 * @Author: Jeremy
 * @Date: 2020/3/13 21:52
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "Jeremy");
    }
}
