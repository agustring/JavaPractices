package pack1;

import java.util.LinkedList;
import java.util.Random;

public class Processor {    
    private LinkedList<Integer> list = new LinkedList<Integer>(); //List will always have 10 objects due to synchronization.
    private final int LIMIT = 10;
    private Object lock = new Object();
        
    public void produce(){        
        int value = 0;
        
        try{
            while(true){
                synchronized(lock){
                    while(list.size()==LIMIT){
                        System.out.println("Goes lock.wait in produce on list size: "+list.size());
                        lock.wait();
                    }
                    list.add(value++);
                    System.out.println("Goes lock.notify in produce on list size: "+list.size());
                    lock.notify();
                }
            }
        }catch (InterruptedException ex) {
        }
    }
    
    public void consume(){
        Random random = new Random();
        
        try{
            while(true){
                synchronized(lock){
                    while(list.size() == 0){
                        System.out.println("Goes lock.wait in consume on list size: "+list.size());
                        lock.wait();                    
                    }
                    System.out.print("List size: "+list.size());
                    int value = list.removeFirst();
                    System.out.println("; value out is: "+ value+"; and goes on notify in consume");
                    lock.notify();
                }
                Thread.sleep(random.nextInt(1000));
            }            
        }catch (InterruptedException ex) {
        }
    }
}
