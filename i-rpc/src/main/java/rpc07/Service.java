package rpc07;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import common.ProductService;
import common.UserService;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jeremy
 * @Date: 2020/3/13 21:52
 */
public class Service {
    private static boolean running = true;
    private static Map<String, Class> clazzMap = new HashMap<>();
    static {
        clazzMap.put("common.UserService", UserServiceImpl.class);
        clazzMap.put("common.ProductService", ProductServiceImpl.class);
    }
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8888);
        while (running){
            Socket s = server.accept();
            process(s);
            s.close();
        }
        server.close();
    }

    private static void process(Socket s) throws Exception{
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
        Hessian2Input hessian2Input = new Hessian2Input(in);

        String className = hessian2Input.readString();
        String methodName = hessian2Input.readString();
        Class[] paramTypes = (Class[])hessian2Input.readObject();
        Object[] args = (Object[]) hessian2Input.readObject();

        Class clazz = clazzMap.get(className);
        Method method = clazz.getMethod(methodName, paramTypes);
        Object object = method.invoke(clazz.newInstance(), args);

        Hessian2Output hessian2Output = new Hessian2Output(out);
        hessian2Output.writeObject(object);
        hessian2Output.flush();
    }
}
