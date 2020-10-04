package adaptor;

/**
 * @Author: Jeremy
 * @Date: 2020/8/25 17:33
 */
public class AdaptorV2 implements ITarget {
    private Adaptee adaptee;

    public AdaptorV2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void f1() {
        // Commission to apaptee
        adaptee.fa();
    }

    public void f2() {
        // reimplement f2() ...
        System.out.println("AdaptorV2 reimplement f2() ...");
    }

    public void fc() {
        adaptee.fc();
    }
}