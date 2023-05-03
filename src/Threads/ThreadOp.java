package Threads;
class UserTh implements  Runnable{

    @Override
    public void run() {
        System.out.println("User Thread");
    }

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        UserTh th = new UserTh();
        Thread t1= new Thread(th);
        System.out.println("current Thread is "+t1.getName());
        t1.setName("UserThread");
        System.out.println("Current Running Thread "+t1.getName());
        System.out.println(t1.threadId());
    }
}
public class ThreadOp {
    public static void main(String[] args) {
        System.out.println("Program started.....");
        Thread t = Thread.currentThread();
        UserTh th = new UserTh();
        Thread t1= new Thread(th);
        t1.start();
        System.out.println("Current Running Thread "+t.getName());
        t.setName("vasanth");
        System.out.println("Current Running Thread "+t.getName());
        System.out.println(t.threadId());
        try{
           Thread.sleep(2000);
        }
        catch (Exception e){
            System.out.println("error");
        }
        System.out.println("Program Ended...");

    }
}
