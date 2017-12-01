package ProcessPackage;

import sun.java2d.loops.FillRect;

import java.util.Iterator;
import java.util.Set;

public class LeftPrintThread implements Runnable {

    private String EmptyString= "                                                    ";
    public GeneralLayer RepLayer;
    public GeneralLayer Layer;
    public StringBuffer s;
    private int i=0;
    private int T=EmptyString.length();

    public Thread t;

    LeftPrintThread(GeneralLayer l1, GeneralLayer l2, StringBuffer ss) {
        t = new Thread(t);
        t.start();
        RepLayer=l1;
        Layer=l2;
        s=ss;
    }

    public void run() {
        try {
            Set<Parameters> Param = RepLayer.getConditions().keySet();
            Iterator<Parameters> It = Param.iterator();
            while (It.hasNext()) {
                Parameters parameter = It.next();
                Integer RepValue = RepLayer.getConditions().get(parameter);
                if (i < 3) {
                    if (!RepValue.equals(Layer.getConditions().get(parameter))) {
                        synchronized (s) {
                            s.append(parameter.toString() + "=" + Layer.getConditions().get(parameter) + " ");
                        }
                        T = T - parameter.toString().length() - 2 - Layer.getConditions().get(parameter).toString().length();
                        i++;
                        if (i == 3) {
                            synchronized (s) {
                                s.append(new StringBuffer(EmptyString.subSequence(0, T)));
                            }
                            i=0;
                            T=EmptyString.length();
                            Thread.sleep(1000);
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
