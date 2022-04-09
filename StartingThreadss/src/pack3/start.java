package pack3;

import java.util.logging.Level;
import java.util.logging.Logger;

//-Third-way--------------------------------------------------------------------
public class start {
    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    System.out.println("Goes on: " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(start.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }            
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    System.out.println("Goes on: " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(start.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }            
            }
        });
        
        t1.start();
        t2.start();
    }
}
