package rpc04;

import common.UserService;

import java.io.IOException;

/**
 * @Author: Jeremy
 * @Date: 2020/3/13 21:49
 */
public class Client {
    public static void main(String[] args) throws IOException {
        UserService service = Stub.getStub();
        System.out.println(service.findById(123).getName());
    }
}
