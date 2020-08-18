package rpc07;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Author: Jeremy
 * @Date: 2020/3/14 13:49
 */
public class HessianUtil {
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
}
