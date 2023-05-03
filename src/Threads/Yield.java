package Threads;

import static java.lang.Thread.currentThread;

public class Yield implements Runnable {

    @Override
    public void run() {
        System.out.println(currentThread().getPriority());
        for (int i = 0; i < 10; i++) {
            System.out.println(currentThread().getName() + " Child Thread " + i);
            Thread.yield();
        }
    }

}
class YieldDemo{

    public static void main(String[] args) {
        Yield y = new Yield();
        Thread t = new Thread(y);
        t.start();
        System.out.println(currentThread().getPriority());
        for (int i = 0; i < 10; i++) {
            System.out.println(currentThread().getName()+" Main Thread "+i);

        }
    }
}
