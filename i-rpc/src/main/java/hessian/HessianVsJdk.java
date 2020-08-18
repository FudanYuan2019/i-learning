package hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import common.User;

import java.io.*;

/**
 * @Author: Jeremy
 * @Date: 2020/3/14 13:26
 */
public class HessianVsJdk {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User(1, "Jeremy");
        System.out.println(String.format("jdk serialize length: %d", jdkSerialize(user).length));
        System.out.println(String.format("hessian serialize length: %d", hessionSerialize(user).length));

        System.out.println((User)jdkDeserialize(jdkSerialize(user)));
        System.out.println((User)hessianDeserialize(hessionSerialize(user)));
    }

    public static byte[] hessionSerialize(Object o) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);
        hessian2Output.writeObject(o);
        hessian2Output.flush();

        byteArrayOutputStream.close();
        hessian2Output.close();

        return byteArrayOutputStream.toByteArray();

    }

    public static Object hessianDeserialize(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Hessian2Input hessian2Input = new Hessian2Input(byteArrayInputStream);
        return hessian2Input.readObject();
    }

    public static byte[] jdkSerialize(Object o) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(o);
        objectOutputStream.flush();

        objectOutputStream.close();
        byteArrayOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static Object jdkDeserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }
}
