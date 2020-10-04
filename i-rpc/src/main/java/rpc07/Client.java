package rpc07;

import common.ProductService;
import common.UserService;

import java.io.IOException;

/**
 * 获取所有接口的所有方法
 * @Author: Jeremy
 * @Date: 2020/3/13 21:49
 */
public class Client {
    public static void main(String[] args) throws IOException {
        UserService userService = (UserService) Stub.getStub(UserService.class);
        System.out.println(userService.findById(123).getName());

        ProductService productService = (ProductService) Stub.getStub(ProductService.class);
        System.out.println(productService.findById(123).getName());
    }
}
