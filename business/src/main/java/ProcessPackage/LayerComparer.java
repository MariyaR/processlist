package ProcessPackage;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class LayerComparer {

    private GeneralLayer LeftLayer, RightLayer, RefLayer;
    private Boolean b;
    private String EmptyString= "                                                    ";
    private StringBuffer st;

    LayerComparer (GeneralLayer l1, GeneralLayer l2, boolean b) {
        LeftLayer=l1;
        RightLayer=l2;
        this.b=b;
    }

    LayerComparer (GeneralLayer l1, GeneralLayer l2, GeneralLayer l3, boolean b) {
        LeftLayer=l1;
        RightLayer=l2;
        this.b=b;
        RefLayer=l3;
    }

    public String compare2() {
        StringBuffer s= new StringBuffer();
        StringBuffer t= new StringBuffer();
        int i=0;

        if (!Objects.equals(LeftLayer.getFunction(), RightLayer.getFunction())) {
            s= new StringBuffer("layers are different");
            return s.toString();
        }

        st=new StringBuffer(EmptyString.subSequence(0,EmptyString.length()
                - LeftLayer.getClass().getSimpleName().length()
                - LeftLayer.getFunction().toString().length()-1));
        s.append(LeftLayer.getFunction().toString()+" "+ LeftLayer.getClass().getSimpleName() +
                st +LeftLayer.getFunction().toString()+" "+ LeftLayer.getClass().getSimpleName()+ "\n");
        Set<Parameters> Param = LeftLayer.getConditions().keySet();
        Iterator<Parameters> It= Param.iterator();
        while (It.hasNext()) {
            Parameters parameter=It.next();
            Integer ThisValue =LeftLayer.getConditions().get(parameter);
            Integer LValue =RightLayer.getConditions().get(parameter);
            if (!ThisValue.equals(LValue)) {
                st=new StringBuffer(EmptyString.subSequence(0,EmptyString.length()-
                        parameter.toString().length()-1-ThisValue.toString().length()));
                s.append(parameter.toString() + "=" + ThisValue + st + parameter.toString()+ "=" + LValue + "\n");
            }
            else {
                if (i<3) {
                    t.append(parameter.toString() + "=" + ThisValue + " ");
                    i++;
                }
                else {
                    t.append("\n");
                    t.append(parameter.toString() + "=" + ThisValue + " ");
                    i=0;
                }
            }

        }
        if (b==true){
            s.append(t);
        }
        return s.toString();
    }

    public String compare3 () {
        StringBuffer s=new StringBuffer();
        st=new StringBuffer(EmptyString.subSequence(0,EmptyString.length()
                - LeftLayer.getClass().getSimpleName().length()
                - LeftLayer.getFunction().toString().length()-1));
        s.append(LeftLayer.getFunction().toString()+" "+ LeftLayer.getClass().getSimpleName() +
                st +RightLayer.getFunction().toString()+" "+ RightLayer.getClass().getSimpleName()+ "\n");
        Semaphore sem = new Semaphore(1);
        new LeftPrintThread(RefLayer, LeftLayer,s, sem);
        new RightPrintThread(RefLayer,RightLayer,s, sem);
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {e.printStackTrace();}
        s.append("\n");

        return s.toString();
    }


}