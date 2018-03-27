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

        LeftLayer.initConditions();
        RightLayer.initConditions();

        if (!Objects.equals(LeftLayer.getFunction(), RightLayer.getFunction())) {
            s= new StringBuffer("layers are different");
            return s.toString();
        }

        st=new StringBuffer(EmptyString.subSequence(0,EmptyString.length()
                - LeftLayer.getLayerName().length()
                - LeftLayer.getFunction().toString().length()-1));
        s.append(LeftLayer.getFunction().toString()+" "+ LeftLayer.getLayerName() +
                st +RightLayer.getFunction().toString()+" "+ RightLayer.getLayerName()+ "\n");
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
                    i=1;
                }
            }
        }
        if (b){
            s.append(t);
        }


        s.append("\n\n");
        return s.toString();
    }

    public String compare3 () {

        StringBuffer s=new StringBuffer();

        if ((LeftLayer!=null)&&(RightLayer!=null)) {

            LeftLayer.initConditions();
            RightLayer.initConditions();
            st = new StringBuffer();
            int length = EmptyString.length() - LeftLayer.getFunction().toString().length()
                    -LeftLayer.getLayerName().length() - 2;
            st.append(EmptyString.subSequence(0, length));
            s.append(LeftLayer.getFunction().toString() + " " + LeftLayer.getLayerName()+" "+
                    st + RightLayer.getFunction().toString() + " "+RightLayer.getLayerName() + "\n");
//old version
//            Semaphore sem = new Semaphore(1);
//            new LeftPrintThreadIncorrect(RefLayer, LeftLayer, s, sem);
//            new RightPrintThreadIncorrect(RefLayer, RightLayer, s, sem);
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            Semaphore leftSem =new Semaphore(3);
            Semaphore rightSem=new Semaphore(0);

            new LeftPrinter(RefLayer,LeftLayer,s,leftSem,rightSem);
            new RightPrinter(RefLayer,RightLayer,s,leftSem,rightSem);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            s.append("\n");
        }

        else if (LeftLayer==null) {
            int i=0;
            RightLayer.initConditions();
            RefLayer.initConditions();
            s.append(EmptyString+RightLayer.getFunction()+" "+RightLayer.getLayerName()+"\n");
            Set<Parameters> Param = RightLayer.getConditions().keySet();
            Iterator<Parameters> It= Param.iterator();
            while (It.hasNext()) {
                Parameters parameter=It.next();
                Integer ThisValue =RightLayer.getConditions().get(parameter);
                Integer RValue =RefLayer.getConditions().get(parameter);
                if (!ThisValue.equals(RValue)) {
                    if (i==0) {
                        s.append(EmptyString).append(parameter.toString()).append("=").append(ThisValue).append(" ");
                        i++;
                    }
                    if (i<3 && i>0) {
                        s.append(parameter.toString()).append("=").append(ThisValue).append(" ");
                        i++;
                    }
                    else if (i==3){
                        s.append("\n").append(EmptyString);
                        s.append(parameter.toString()).append("=").append(ThisValue).append(" ");
                        i=1;
                    }
                }

            }

            s.append("\n\n");

        }

        else if (RightLayer==null){
            int i=0;
            LeftLayer.initConditions();
            RefLayer.initConditions();
            s.append(LeftLayer.getFunction().toString()+" "+LeftLayer.getLayerName()+"\n");
            Set<Parameters> Param = LeftLayer.getConditions().keySet();
            Iterator<Parameters> It= Param.iterator();
            while (It.hasNext()) {
                Parameters parameter=It.next();
                Integer ThisValue =LeftLayer.getConditions().get(parameter);
                Integer RValue =RefLayer.getConditions().get(parameter);

                if (!ThisValue.equals(RValue)) {
                    if (i<3) {
                        s.append(parameter.toString() + "=" + ThisValue + " ");
                        i++;
                    }
                    else {
                        s.append("\n");
                        s.append(parameter.toString() + "=" + ThisValue + " ");
                        i=1;
                    }
                }

            }

                s.append("\n\n");


        }

        return s.toString();
    }

}
