package ProcessPackage;

import sun.java2d.loops.FillRect;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class LeftPrintThread implements Runnable {

    private Semaphore sem;
    private String EmptyString= "                                                    ";
    public GeneralLayer RepLayer;
    public GeneralLayer Layer;
    public StringBuffer s;
    private int i=0;
    private int T=EmptyString.length();

    public Thread t;

    LeftPrintThread(GeneralLayer l1, GeneralLayer l2, StringBuffer s) {
        t = new Thread(this);
        t.start();
        RepLayer=l1;
        Layer=l2;
        this.s=s;
        //this.sem=sem;
    }

    public void run() {
        try {
            //sem.acquire();
            Set<Parameters> Param = RepLayer.getConditions().keySet();
            Iterator<Parameters> It = Param.iterator();
            while (It.hasNext()) {
                //sem.acquire();
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
                            //sem.release();
                            Thread.sleep(10);
                        }
                    }
                }
            }
        }

        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
