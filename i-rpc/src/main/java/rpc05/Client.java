package rpc05;

import common.UserService;

import java.io.IOException;

/**
 * 只能获取一个接口的方法
 * @Author: Jeremy
 * @Date: 2020/3/13 21:49
 */
public class Client {
    public static void main(String[] args) throws IOException {
        UserService service = Stub.getStub();
        System.out.println(service.findById(123).getName());
    }
}
