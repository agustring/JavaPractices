package pack1;

//NETBEANS TAKE 300ms TO EXCECUTE!

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker {
    
    private Random random = new Random();
    //---------------SOLUTION---------------------------------------------------
    private Object lock1 = new Object();
    private Object lock2 = new Object();    
    //--------------------------------------------------------------------------
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    
    //try 'public synchronized void stageOne()' and two, see what happends w/exc_time
    
    public void stageOne(){
        synchronized(lock1){
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
            }
            list1.add(random.nextInt(100));
        }
    }
    
    //when lock monitor logs a thread has to wait another to finish in orden to take action.
    
    public void stageTwo(){ 
        synchronized(lock2){
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
            }
            list2.add(random.nextInt(100));  
            //no, two threads can't run the same code at the same time
        }
    }   
    
    public void process(){
        for(int i=0; i<100; i++){
            stageOne();
            stageTwo();
        }
    }
    
    public void main(){
        
        System.out.println("Start the show...");
        
        long ts = System.currentTimeMillis();
        
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run(){
                process();
            }
        });

        //Problem starts when you try to access data from multiple threads.
        
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run(){
                process();
            }
        });
        
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
        }

        long te = System.currentTimeMillis();
        
        System.out.println("It ended at: "+(te-ts)+"ms.");
        System.out.println("List1: "+list1.size()+"; List2: "+list2.size());
    }
}
