package pack1;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private int count = 0;
    private Lock lock = new ReentrantLock(); //Look https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantLock.html
    private Condition cond = lock.newCondition(); //Look https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Condition.html
    
    private void increment(){
        for(int i=0; i<10000; i++){
            count++;
        }
    }
    
    public void firstThread(){
        try {
            lock.lock(); //equals to syncronized buy low level
            System.out.println("firstThread cond.await;");
            cond.await();
            try{
                increment();
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException ex) {
        }
    }
    
    public void secondThread(){              
        try {            
            Thread.sleep(1000);
            lock.lock();
            System.out.println("secondThread is wait for return key;");
            new Scanner(System.in).nextLine();
            System.out.println("Got it! cond.signal;");            
        } catch (InterruptedException ex) {
        }
        
        cond.signal();
        System.out.println("Going;");
        
        try{
            increment();
        } finally {
            lock.unlock();
        }
    }
    
    public void finish(){
        System.out.println("Count is: "+ count);
    }
}
