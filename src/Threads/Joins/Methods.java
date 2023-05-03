package Threads.Joins;

public class Methods {

    public void m1(){
        for (int i = 0; i < 10; i++) {

            System.out.println(Thread.currentThread().getName()+" "+i+" m1");
        }
    }

    public void m2(){
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i+" m2");
        }
    }

    public void m3(){
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i+" m3");
        }
    }
}

class MyThread extends Thread{
    Methods m;

    public MyThread(Methods m) {
        this.m = m;
    }


    public void run(){
      MyThread1 m1 = new MyThread1(m);
        try {
            m1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        m.m1();
    }
}
class MyThread1 extends Thread{
    Methods m;

    public MyThread1(Methods m) {
        this.m = m;
    }

    public void run(){
        MyThread3 m1 = new MyThread3(m);
        try {
            m1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        m.m2();
    }
}

class MyThread3 extends Thread{
    Methods m;

    public MyThread3(Methods m) {
        this.m = m;
    }

    public void run(){
        m.m3();
    }
}

class Main{
    public static void main(String[] args) {
        Methods m = new Methods();
        MyThread m1 = new MyThread(m);
        m1.start();
        MyThread1 m2 = new MyThread1(m);
        m2.start();
        MyThread3 m3 = new MyThread3(m);
        m3.start();
    }
}
