package rpc02;

import common.User;

import java.io.IOException;

/**
 * @Author: Jeremy
 * @Date: 2020/3/13 21:49
 */
public class Client {
    public static void main(String[] args) throws IOException {
        User user = Stub.findById(123);
        System.out.println(user.getName());
    }
}
