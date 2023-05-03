package Threads;

import java.util.concurrent.locks.ReentrantLock;

public class Main implements  Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("MyThread " + i);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        Thread t = new Thread(m);
        Thread1 t1 = new Thread1();
        t1.start();
        t.start();
        System.out.println("hello");
    }
}

//    ReentrantLock lock = new ReentrantLock();
//    MyThread t = new MyThread();
//    MyThread1 t1 = new MyThread1();
//            lock.lock();
//                    try {
//                    t.start();
//                    t1.start();
//                    } finally {
//                    lock.unlock();
//                    }



