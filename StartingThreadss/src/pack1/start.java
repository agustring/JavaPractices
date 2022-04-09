package pack1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * there are 3 ways to start a thread
 * @author agust
 */

//-First-way--------------------------------------------------------------------
class Runner extends Thread{
    @Override
    public void run(){
        for(int i=0; i<10; i++){
            System.out.println("Goes on: "+i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

public class start {

    public static void main(String[] args) {
        Runner runner1 = new Runner();
        runner1.start();
        
        Runner runner2 = new Runner();
        runner2.start();
    }
}
