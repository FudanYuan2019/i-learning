package lock;

/**
 * @Author: Jeremy
 * @Date: 2020/9/15 12:20
 */
public class DeadLockExample {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        Object o2 = new Object();
        Thread thread1 = new Thread(new Thread1(o1, o2));
        Thread thread2 = new Thread(new Thread1(o2, o1));
        thread1.start();
        thread2.start();
    }

    private static class Thread1 implements Runnable {
        private Object lockA;
        private Object lockB;
        public Thread1(Object lockA, Object lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            synchronized (lockA) {
                try {
                    System.out.println("get lockA");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println("get lockB");
                }
            }
        }
    }
}