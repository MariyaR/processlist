package ProcessPackage;

import sun.java2d.loops.FillRect;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class LeftPrintThreadIncorrect implements Runnable {

    private Semaphore sem;
    private String EmptyString= "                                                    ";
    private GeneralLayer RepLayer;
    private GeneralLayer Layer;
    private StringBuffer s;
    private int i=0;
    private int T=EmptyString.length();

    private Thread t;

    LeftPrintThreadIncorrect(GeneralLayer l1, GeneralLayer l2, StringBuffer s, Semaphore sem) {
        t = new Thread(this);
        t.start();
        RepLayer=l1;
        Layer=l2;
        this.s=s;
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
                        T = T - parameter.toString().length() - 2 - Layer.getConditions().get(parameter).toString().length();
                        i++;
                        if (i == 3) {
                            s.append(new StringBuffer(EmptyString.subSequence(0, T)));
                            i=0;
                            T=EmptyString.length();
                            sem.release();
                            Thread.sleep(10);
                        }
                    }
                }
            }
            if (i<3) {
                s.append(new StringBuffer(EmptyString.subSequence(0, T)));
            }
            sem.release();
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
