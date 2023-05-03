package Threads;

class MyThread extends Thread{
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println(currentThread().getName()+" "+i);
            try {
                Thread.currentThread().sleep(2000);
            }
            catch (InterruptedException e){
                System.out.println(e.toString());
            }
        }
    }
}

public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        t.start();
       // t.join();
      //  t.join(1000);
        t.join(1000,10);
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }
}
