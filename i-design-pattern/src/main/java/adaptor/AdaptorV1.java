package adaptor;

/**
 * @Author: Jeremy
 * @Date: 2020/8/25 17:33
 */
public class AdaptorV1 extends Adaptee implements ITarget {
    public void f1() {
        super.fa();
    }

    public void f2() {
        // reimplement f2() ...
        System.out.println("AdaptorV1 reimplement f2() ...");
    }
}