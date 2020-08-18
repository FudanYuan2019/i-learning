package rpc05;

import common.User;
import common.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                String methodName = method.getName();
                Class[] paramsType = method.getParameterTypes();
                oos.writeUTF(methodName);
                oos.writeObject(paramsType);
                oos.writeObject(args);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User user = (User) ois.readObject();

                oos.close();
                socket.close();

                return user;
            }
        };

        Object o = Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, h);
        System.out.println(o.getClass().getInterfaces()[0]);
        return (UserService) o;
    }
}
