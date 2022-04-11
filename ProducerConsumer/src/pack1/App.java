package pack1;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

// see differences with https://www.youtube.com/watch?v=Vrt5LqpH2D0&list=PLBB24CFB073F1048E&index=7

public class App {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10); //Integer queue size 10
    
    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                consumer();
            }
        });
        
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                producer();
            }
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
        }
        
    }
    
    private static void producer(){
        Random random = new Random();
        while(true){
            try {
                queue.put(random.nextInt(100)); //It will wait if it's full.            
            } catch (InterruptedException ex) {
            }
        }
    }
    
    private static void consumer(){
        Random random = new Random();
        
        try {
            Thread.sleep(300);
            while(true){
                if (random.nextInt(10) == 0){
                    Integer value = queue.take();

                    System.out.println("Value taken was "+ value + "; Queue size: "+ queue.size());
                }
            }
        } catch (InterruptedException ex) {
        }
        
    }
    
}
