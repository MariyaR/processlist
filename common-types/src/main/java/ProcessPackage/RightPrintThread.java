package ProcessPackage;

import java.util.Iterator;
import java.util.Set;

public class RightPrintThread implements Runnable {

    public GeneralLayer RepLayer;
    public GeneralLayer Layer;
    public StringBuffer s;
    private int i=0;


    public Thread t;

    RightPrintThread(GeneralLayer l1, GeneralLayer l2, StringBuffer ss) {
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
                        s.append(parameter.toString() + "=" + Layer.getConditions().get(parameter) + " ");
                        i++;
                        if (i == 3) {
                            s.append("\n");
                            i=0;
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
