package Threads;

import java.util.concurrent.locks.ReentrantLock;

public class Thread1 extends  Thread{

        public  void  run(){
            System.out.println(currentThread().getPriority()+" Mythread1 priority");
            for (int i = 15; i > 0; i--) {
                System.out.println("MyThread1 " + i);
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }

        }

        public static void main(String[] args) throws  Exception {
            Thread1 t = new Thread1();
            t.setPriority(10);
            t.start();

            Demo1 d = new Demo1();
         Thread th = new Thread(d);
          th.start();
            System.out.println(currentThread().getPriority()+" Main thread priority");
            for (int i = 0; i < 15; i++) {
                System.out.println("Main Thread");
            }
        }




    }



