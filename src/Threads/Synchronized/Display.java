package Threads.Synchronized;

import Threads.Interrupt;

public class Display {
    Integer x=10;
    public synchronized void  display(String name){
        for (int i = 0; i < 10; i++) {
            System.out.print("Good Morning: ");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println(e.toString());
            }
            System.out.println(name);
            System.out.println(Thread.currentThread().getName()+" "+i);

        }
    }

    public synchronized void displayn(){
        for (int i = 1; i <=10; i++) {
            System.out.print(i+" ");
            try {
                Thread.sleep(2000);
            }catch (Exception e){
                System.out.println("Sleep Interrupted");
            }
        }
    }
    
    public synchronized void displayc(){
        for (int i = 65; i <= 75 ; i++) {
            System.out.print((char)(i)+" ");
            try {
                Thread.sleep(2000);
            }catch (Exception e){
                System.out.println("Sleep Interrupted");
            }
        }
    }

    public  void displayb(String name){

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }

        //synchronized (this) { //current object - object level lock
        synchronized (Display.class){ //class level lock
            for (int i = 0; i < 10; i++) {
                System.out.print("Good Morning: ");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
                System.out.println(name);
            }
        }


        for (int i = 0; i < 10; i++) {
            System.out.println(i+" "+Thread.currentThread().threadId());
        }
    }

    public void increment(){
        synchronized (Display.class) {
            for (int i = 0; i < 5; i++) {
                x++;
                System.out.println(x + " " + Thread.currentThread().getName());
            }
        }


    }
}

class MyThread extends Thread{
    Display d;
    String name;

    MyThread(Display d, String name){
        this.d = d;
        this.name = name;
    }
    public void run(){
        d.display(name);
    }
}

class MyThread1 extends Thread{
    Display d;


    MyThread1(Display d){
        this.d = d;

    }
    public void run(){
        d.displayn();
    }
}

class MyThread2 extends Thread{
    Display d;


    MyThread2(Display d){
        this.d = d;
    }
    public void run(){
        d.displayc();
    }
}

class MyThread3 extends Thread{
    Display d;
    String name;

    public MyThread3(Display d, String name) {
        this.d = d;
        this.name = name;
    }

    public void run(){
        d.displayb(name);
    }
}
class MyThread4 extends  Thread{
    Display d;

    public MyThread4(Display d) {
        this.d = d;
    }

    public void run(){
        d.increment();
    }
}
class SyncDemo{
    public static void main(String[] args) throws InterruptedException {
        Display d = new Display();
        Display d1 = new Display();
        MyThread t1 = new MyThread(d,"Vasanth");
        MyThread t2 = new MyThread(d,"Kumar");
        MyThread t3 = new MyThread(d,"Dunphy");
        MyThread t4 = new MyThread(d,"Mohan");
        MyThread t5 = new MyThread(d1,"Yuvi");
//        t1.start();
//        t2.start();
//        t5.start(); //if the method is static, two display objects, irregular output and if the
        //method is static , we will get regular output(even with different objects)
//        t3.start();
//        t4.start();
        //if we want only one thread to act on the method which is there inside run, declare the method as synchronized.

//        MyThread1 t6 = new MyThread1(d);
//        MyThread2 t7 = new MyThread2(d);
//        t6.start();
//        t7.start();

        MyThread3 t8 = new MyThread3(d,"Yuvi");
      //  MyThread3 t9 = new MyThread3(d,"Bhuvi");
        MyThread3 t9 = new MyThread3(d1,"Bhuvi");
//        t8.start();
//        t9.start();

        MyThread4 t10 = new MyThread4(d);
        MyThread4 t11 = new MyThread4(d1);
        t10.start();
        t11.start();

    }
}

