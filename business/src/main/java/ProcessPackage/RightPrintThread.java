package ProcessPackage;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class RightPrintThread implements Runnable {

    public GeneralLayer RepLayer;
    public GeneralLayer Layer;
    public StringBuffer s;
    private int i=0;
    private Semaphore sem;

    public Thread t;

    RightPrintThread(GeneralLayer l1, GeneralLayer l2, StringBuffer ss, Semaphore sem) {
        t = new Thread(this);
        t.start();
        RepLayer=l1;
        Layer=l2;
        s=ss;
        this.sem=sem;
    }

    public void run() {
        try {
            Set<Parameters> Param = RepLayer.getConditions().keySet();
            Iterator<Parameters> It = Param.iterator();
            while (It.hasNext()) {
                if (i==0) {sem.acquire();}
                Parameters parameter = It.next();
                Integer RepValue = RepLayer.getConditions().get(parameter);
                if (i < 3) {
                    if (!RepValue.equals(Layer.getConditions().get(parameter))) {
                        s.append(parameter.toString() + "=" + Layer.getConditions().get(parameter) + " ");
                        i++;
                        if (i == 3) {
                            s.append("\n");
                            i=0;
                            sem.release();
                            Thread.sleep(10);
                        }
                    }
                }
            }
            sem.release();
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
