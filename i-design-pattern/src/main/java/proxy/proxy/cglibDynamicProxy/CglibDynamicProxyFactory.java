package proxy.proxy.cglibDynamicProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: Jeremy
 * @Date: 2020/8/22 19:18
 */
public class CglibDynamicProxyFactory implements MethodInterceptor {
    private Object target;

    public CglibDynamicProxyFactory(Object target) {
        this.target = target;
    }

    // generate proxy object for target object.
    public Object getProxyInstance() {
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }

    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("%s elapsed %d ms", method.getName(), endTime - startTime));
        return result;
    }
}
