package rpc07;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

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

                String className = clazz.getName();
                String methodName = method.getName();
                Class[] paramsType = method.getParameterTypes();

                Hessian2Output hessian2Output = new Hessian2Output(socket.getOutputStream());
                hessian2Output.writeString(className);
                hessian2Output.writeString(methodName);
                hessian2Output.writeObject(paramsType);
                hessian2Output.writeObject(args);
                hessian2Output.flush();

                Hessian2Input hessian2Input = new Hessian2Input(socket.getInputStream());
                Object object = hessian2Input.readObject();

                hessian2Output.close();
                socket.close();

                return object;
            }
        };
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, h);
        System.out.println(o.getClass().getInterfaces()[0]);
        return o;
    }
}
