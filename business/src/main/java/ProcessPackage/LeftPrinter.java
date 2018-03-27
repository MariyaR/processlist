package ProcessPackage;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class LeftPrinter implements Runnable {

    private Semaphore leftSem;
    private Semaphore rightSem;
    private String EmptyString= "                                                    ";
    private GeneralLayer RefLayer;
    private GeneralLayer Layer;
    private StringBuffer s;
    private int i=0;
    private int length=EmptyString.length();

    private Thread t;

    LeftPrinter(GeneralLayer l1, GeneralLayer l2, StringBuffer s, Semaphore leftSem, Semaphore rightSem) {
        t = new Thread(this);
        t.start();
        RefLayer=l1;
        Layer=l2;
        this.s=s;
        this.leftSem=leftSem;
        this.rightSem=rightSem;
    }

    public void run() {
        try {
            Set<Parameters> Param = RefLayer.getConditions().keySet();
            Iterator<Parameters> It = Param.iterator();
            while (It.hasNext()) {

                Parameters parameter = It.next();
                Integer RefValue = RefLayer.getConditions().get(parameter);
                Boolean condition=!RefValue.equals(Layer.getConditions().get(parameter));

                if (condition) {
                    leftSem.acquire();
                    s.append(parameter.toString() + "=" + Layer.getConditions().get(parameter) + " ");
                    length = length - parameter.toString().length() - 2 - Layer.getConditions().get(parameter).toString().length();
                    i++;

                    if (i==3) {
//                        if() {
                            s.append(new StringBuffer(EmptyString.subSequence(0, length)));
                            length=EmptyString.length();
                            rightSem.release(3);
//                       }
//                        else if() {
//                            s.append("\n");
//                        }
                        i=0;
                    }

                }

            }
            if(i!=0) {
                s.append(new StringBuffer(EmptyString.subSequence(0, length)));
                length = EmptyString.length();
            }
            rightSem.release(12);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
