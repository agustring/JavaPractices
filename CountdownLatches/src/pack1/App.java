package pack1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

//To access the same variable from different threads at the same time.
//High level multiclass threading.

class Proc implements Runnable{
    
    private CountDownLatch latch;
    
    public Proc(CountDownLatch latch){
        this.latch = latch;
    }
    
    public void run(){
        System.out.println("Started.");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }
        
        latch.countDown();
        System.out.println("Completed.");
    }
}

public class App {
    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(3);
        
        ExecutorService exe = Executors.newFixedThreadPool(3);
        
        for(int i=0; i<3; i++){
            exe.submit(new Proc(latch));
        }
        try {
            latch.await();
        } catch (InterruptedException ex) {
        }
        
        System.out.println("All Completed.");
        
        //Starts 3 at same time.
    }
}
