package pack1;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
* OK here's the thing, so u may think it's all connected but
* you are just changing 'running' to false in an excecution that
* is happening in the main thread so it main not take effect
* the solution is to make this variable 'VOLATILE' so u can access
* to the same one from whichever thread.
*/

class Proc extends Thread{
    private volatile boolean running = true; //can be changed from wherever
    
    public void run(){
        while(running){
            System.out.println("Hey babe...");
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Proc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void shutdown(){
        running = false;
    }
}

public class basic {
    public static void main (String[] args){
        
        Proc p1 = new Proc();
        p1.start();
        
        System.out.println("Press any key to stop!");
        Scanner s = new Scanner(System.in);
        s.nextLine();
        
        p1.shutdown();
    }
}
