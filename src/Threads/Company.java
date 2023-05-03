package Threads;

public class Company {
    int n;
    boolean f = false;
    //f:false producer
    //f:true consumer
    synchronized  public  void produceItem(int n){
        if(f){
            try{
                wait();
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
        this.n = n;
        System.out.println("Produced: "+this.n);
        f=true;
        notify();
    }
    synchronized public int consumeItem() {
        if(!f){
            try{
              wait();
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
        System.out.println("Consumed: "+this.n);
        f=false;
        notify();
        return this.n;
    }
}
