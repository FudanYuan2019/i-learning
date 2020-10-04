package rpc06;

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
        ObjectInputStream ois = new ObjectInputStream(in);

        String className = ois.readUTF();
        String methodName = ois.readUTF();
        Class[] paramTypes = (Class[])ois.readObject();
        Object[] args = (Object[]) ois.readObject();

        Class clazz = clazzMap.get(className);
        Method method = clazz.getMethod(methodName, paramTypes);
        Object object = method.invoke(clazz.newInstance(), args);

        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(object);
        oos.flush();
    }
}
