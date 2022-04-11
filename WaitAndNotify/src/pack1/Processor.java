package pack1;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Processor {
    public void produce(){
        synchronized(this){ //intrincic lock
            System.out.println("Producer is running...");
            try {
                wait(); //It relinquish the lock to other process
            } catch (InterruptedException ex) {
            }
            System.out.println("Producer resumed...");
        }
    }
    public void consume(){
        
        Scanner scan = new Scanner(System.in);
        
        try {
            Thread.sleep(2000);
            synchronized(this){
                System.out.println("Looking for return key to be pressed!");
                scan.nextLine();
                System.out.println("Key pressed.");
                notify(); //Reliquish power and releases wait from producer.
                Thread.sleep(5000);
            }
        } catch (InterruptedException ex) {
        }
        
        
    }
}
