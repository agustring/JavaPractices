package pack1;

import java.util.logging.Level;
import java.util.logging.Logger;

//try to print a variable modified by 2 different threads at the same time
//look what happends without 'synchronized' keyword

public class Synckey {
    
    private int c = 0; //try making it volatile
    
    public static void main(String[] args){
        Synckey synckey = new Synckey();
        synckey.doSomething();
    }
    
    //only way they reach 20000 in the console output
    public synchronized void inc() { //intrinsic lock by this method
        c++;
    }
    
    public void doSomething(){
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i=0; i<10000; i++){
                    //c++; //check diff with 'c=c+1;'
                    inc();
                }
            }
            
        });
        
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i=0; i<10000; i++){
                    //c++;
                    inc();
                }
            }            
        });
        
        t1.start();
        t2.start();
        //after start u must .join() threads in orden to remain existing.
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Synckey.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        System.out.println("The count is: "+c);
    }
}
