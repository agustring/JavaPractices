
package pack2;

import java.util.logging.Level;
import java.util.logging.Logger;

//-Second-way-------------------------------------------------------------------

class Runner implements Runnable{
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

public class start{

    public static void main(String[] args) {
        //Runner runner1 = new Runner();  has the same result
        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());
        t1.start();
        t2.start();
    }
}
