package Threads;

public class Producer implements  Runnable{
    Company c;

    public Producer(Company c){
        this.c = c;
    }

    @Override
    public void run() {
        int i=1;
        while (true){
            this.c.produceItem(i);
            try{
                Thread.sleep(2000);
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
            i++;
        }
    }
}
