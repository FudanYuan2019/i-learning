package rpc02;

import common.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @Author: Jeremy
 * @Date: 2020/3/14 11:41
 */
public class Stub {
    public static User findById(Integer id) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);

        socket.getOutputStream().write(baos.toByteArray());
        socket.getOutputStream().flush();

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        id = dis.readInt();
        String name = dis.readUTF();
        User user = new User(id, name);

        dos.close();
        socket.close();

        return user;
    }
}
