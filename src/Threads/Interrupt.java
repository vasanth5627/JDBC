package Threads;

public class Interrupt extends Thread {
    public void run(){
        try{
            for (int i = 0; i < 10; i++) {
                System.out.println(currentThread().getName()+" "+i);
                if(i==8)
                Thread.sleep(1000);
        }

        }
        catch (InterruptedException exception){
            System.out.println("I got interrupted");
        }
    }
}

class InterruptMain{
    public static void main(String[] args) throws InterruptedException {
        Interrupt i = new Interrupt();
       // i.join(); cant join before start
        i.start();
       // i.join(); //join after start
        i.interrupt();// it will wait until thread goes into sleeping or waiting state

        System.out.println("End of Main");
    }
}
