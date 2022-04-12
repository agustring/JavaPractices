package pack1;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {
    private Account acc1 = new Account();
    private Account acc2 = new Account();
    
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    
    public void acquireLocks(Lock lock1, Lock lock2){
        while(true){ //Because I really need this to happend
            boolean gotFirst = false;
            boolean gotSecond = false;
            
            try{
                gotFirst = lock1.tryLock();
                gotSecond = lock2.tryLock();
            } finally {
                if(gotFirst && gotSecond){
                    return;
                }
                
                if(gotFirst){
                    lock1.unlock();
                }
                
                if(gotSecond){
                    lock2.unlock();
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
            }            
        }
    }
    
    public void firstThread(){
        Random random = new Random();
                
        for(int i=0; i<10000; i++){
            /*
            lock1.lock();
            lock2.lock();
            */
            
            acquireLocks(lock1,lock2);
            
            try{
                Account.transfer(acc1,acc2,random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
    
    public void secondThread(){
        Random random = new Random();

        for(int i=0; i<10000; i++){
            /*
            lock2.lock();
            lock1.lock(); //It got stucked because u never release both locks if u get them inverted.
            */
            acquireLocks(lock2,lock1); //Inverted
            
            try{
                Account.transfer(acc2,acc1,random.nextInt(100));
            } finally {
                lock2.unlock();
                lock1.unlock();
            }
        }
        
    }
   
    
    public void finish(){
        System.out.println("Account 1 balance is: "+ acc1.getBalance());
        System.out.println("Account 2 balance is: "+ acc2.getBalance());
        System.out.println("Total balance is: "+ (acc1.getBalance()+acc2.getBalance()));
    }
}
