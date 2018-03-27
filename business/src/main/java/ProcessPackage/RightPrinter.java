package ProcessPackage;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class RightPrinter implements Runnable {

    public GeneralLayer RefLayer;
    public GeneralLayer Layer;
    public StringBuffer s;
    private int i=0;
    private Semaphore leftSem;
    private Semaphore rightSem;

    public Thread t;

    RightPrinter(GeneralLayer l1, GeneralLayer l2, StringBuffer s, Semaphore leftSem, Semaphore rightSem) {
        t = new Thread(this);
        t.start();
        RefLayer=l1;
        Layer=l2;
        this.s=s;
        this.leftSem=leftSem;
        this.rightSem=rightSem;
        int i=0;
    }

    public void run() {
        try {
            Set<Parameters> Param = RefLayer.getConditions().keySet();
            Iterator<Parameters> It = Param.iterator();
            while (It.hasNext()) {

                Parameters parameter = It.next();
                Integer RefValue = RefLayer.getConditions().get(parameter);
                Boolean condition = !RefValue.equals(Layer.getConditions().get(parameter));

                    if (condition) {
                        rightSem.acquire();
                        //System.out.println("Right pr got acquire "+ ++i);
                        //System.out.println(parameter.toString()+" "+Layer.getConditions().get(parameter));
                        s.append(parameter.toString() + "=" + Layer.getConditions().get(parameter) + " ");

                       // System.out.println("s: "+s+" end");
                        i++;
                        if (i==3) {

//                            if () {
                                s.append("\n");
                                leftSem.release(3);
//                            }
//
//                            else if () {
//                                s.append("\n").append(EmptyString);
//                            }

                            i=0;
                        }
                    }

            }
            if (i!=0)
            {
                s.append("\n");
            }
            leftSem.release(12);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
