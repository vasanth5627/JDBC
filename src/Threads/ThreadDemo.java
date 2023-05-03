package Threads;
import java.util.*;
public class ThreadDemo implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
            }
        }

    public static void main(String[] args) {
        ThreadDemo th = new ThreadDemo();
        Thread t= new Thread(th);
        Thread t1 = new Thread();
     //   t.start(); //new thread is created and run method executed
   //    t.run(); //new thread is not created and run method executed using main thread
    //    t1.start(); //empty response but new thread created
   //    t1.run(); //empty response, new thread is not created
     //   th.run(); //new thread is not created and run method executed using main thread
      //  th.start(); //compile time error
    }
}

