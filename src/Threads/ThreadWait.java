package Threads;

public class ThreadWait {

    public static boolean tw1 =false;
    public static boolean tw2 =false;
    public static boolean tw3 =false;
    public static void main(String[] args) {
        ThreadWait1 t1 = new ThreadWait1();
        Thread th1  = new Thread(t1);

        th1.start();
        while(!tw1){
            try{
                Thread.sleep(100);
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
        System.out.println(tw1);
        ThreadWait2 t2 = new ThreadWait2();
        Thread th2  = new Thread(t2);
        th2.start();
        while(!tw2){
            try{
                Thread.sleep(100);
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
        System.out.println(tw2);
        ThreadWait3 t3 = new ThreadWait3();
        Thread th3  = new Thread(t3);
        th3.start();

    }
}

class ThreadWait1 extends ThreadWait implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Thread1 "+i);
        }

        tw1= true;
    }
}

class ThreadWait2 extends ThreadWait implements  Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Thread2 "+i);
        }

        tw2 = true;
    }
}

class ThreadWait3 extends  ThreadWait implements  Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Thread3 "+i);
        }

        tw3=false;
    }
}


