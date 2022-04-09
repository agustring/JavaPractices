package pack1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Proc implements Runnable{
    private int id;
    
    public Proc(int id){
        this.id = id;
    }
    @Override
    public void run(){
        System.out.println("Starting: "+id);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        System.out.println("Finishing: "+id);
    }
}

public class App {
    
    public static void main(String[] args){
        ExecutorService exe = Executors.newFixedThreadPool(3);
        
        for(int i=0; i<10; i++){
            exe.submit(new Proc(i));
        }
        
        exe.shutdown();
        System.out.println("Everything submited.");
        try {
            exe.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
        }
        System.out.println("Everything done.");
    }
}
