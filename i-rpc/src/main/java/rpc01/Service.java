package rpc01;

import common.User;

import java.io.*;
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
        DataInputStream dis = new DataInputStream(in);
        DataOutputStream dos = new DataOutputStream(out);

        int id = dis.readInt();
        User user = new UserServiceImpl().findById(id);
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }
}
