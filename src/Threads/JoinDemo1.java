package Threads;

public class JoinDemo1 extends Thread{
    static Thread mt;
    public void run(){
        try {
            mt.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(currentThread().getName()+" "+i);
        }

    }
}

class joinDemo2 {
    public static void main(String[] args) throws InterruptedException{
        JoinDemo1.mt = Thread.currentThread();
        JoinDemo1 t= new JoinDemo1();
        t.start();
      //  t.join(); -- deadlock
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);

        }
    }
}
