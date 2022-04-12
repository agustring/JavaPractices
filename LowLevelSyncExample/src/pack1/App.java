package pack1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args){
        final Processor proc = new Processor();
        
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                proc.consume();
            }
        });
        
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                proc.produce();
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
}
