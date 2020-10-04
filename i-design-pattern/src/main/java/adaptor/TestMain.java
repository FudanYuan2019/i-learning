package adaptor;

/**
 * @Author: Jeremy
 * @Date: 2020/8/25 17:38
 */
public class TestMain {
    public static void main(String[] args) {
        AdaptorV1 adaptorV1 = new AdaptorV1();
        adaptorV1.f1();
        adaptorV1.f2();
        adaptorV1.fc();

        Adaptee adaptee = new Adaptee();
        AdaptorV2 adaptorV2 = new AdaptorV2(adaptee);
        adaptorV2.f1();
        adaptorV2.f2();
        adaptorV2.fc();
    }
}
