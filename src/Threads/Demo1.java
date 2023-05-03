package Threads;

import static java.lang.Thread.currentThread;

public class Demo1 implements Runnable{
    @Override
    public void run(){
        System.out.println(currentThread().getPriority()+" child thread priority");
        for (int i = 0; i < 15; i++) {
            System.out.println("Child Thread");
        }
    }
}
