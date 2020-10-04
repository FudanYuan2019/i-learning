package rpc06;

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
    public static Object getStub(Class clazz) throws IOException {
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1", 8888);

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                String className = clazz.getName();
                String methodName = method.getName();
                Class[] paramsType = method.getParameterTypes();
                oos.writeUTF(className);
                oos.writeUTF(methodName);
                oos.writeObject(paramsType);
                oos.writeObject(args);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object object = ois.readObject();

                oos.close();
                socket.close();

                return object;
            }
        };
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, h);
        System.out.println(o.getClass().getInterfaces()[0]);
        return o;
    }
}
