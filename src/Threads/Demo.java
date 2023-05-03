package Threads;

public class Demo {
    public static void main(String[] args) {
        Company c = new Company();
        Producer p = new Producer(c);
        Thread th = new Thread(p);
        Consumer co = new Consumer(c);
        Thread th1 = new Thread(co);
        th.start();
        th1.start();
    }
}
