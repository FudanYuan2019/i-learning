package rpc03;

import common.User;
import common.UserService;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @Author: Jeremy
 * @Date: 2020/3/14 11:41
 */
public class Stub {
    public static UserService getStub() throws IOException {
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1", 8888);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                dos.writeInt(123);  // 缺陷，方法名和参数都是写死的

                socket.getOutputStream().write(baos.toByteArray());
                socket.getOutputStream().flush();

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                int userId = dis.readInt();
                String name = dis.readUTF();
                User user = new User(userId, name);

                dos.close();
                socket.close();

                return user;
            }
        };

        Object o = Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, h);
        System.out.println(o.getClass().getInterfaces()[0]);
        return (UserService) o;
    }
}
