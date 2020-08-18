package rpc04;

import common.User;
import common.UserService;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: Jeremy
 * @Date: 2020/3/13 21:52
 */
public class Service {
    private static boolean running = true;
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
        DataOutputStream dos = new DataOutputStream(out);

        String methodName = ois.readUTF();
        Class[] paramTypes = (Class[])ois.readObject();
        Object[] args = (Object[]) ois.readObject();

        UserService service = new UserServiceImpl();
        Method method = service.getClass().getMethod(methodName, paramTypes);
        User user = (User) method.invoke(service, args);
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }
}
