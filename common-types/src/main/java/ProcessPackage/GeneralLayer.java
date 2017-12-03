package ProcessPackage;

import com.sun.javafx.scene.control.skin.IntegerFieldSkin;

import java.util.*;

import static ProcessPackage.Parameters.*;


public abstract class GeneralLayer {

    private Functions Function;
    private String EmptyString= "                                                    ";
    private StringBuffer st=new StringBuffer();
    private LinkedHashMap<Parameters,Integer> Conditions= new LinkedHashMap<Parameters,Integer>();

    public Functions getFunction() {return Function;}
    protected void setFunction(Functions Function) {this.Function=Function;}
    public void setConditions (LinkedHashMap<Parameters,Integer> M){
        this.Conditions=M;
    }
    public  LinkedHashMap<Parameters,Integer> getConditions (){
        return Conditions;
    }
    private void initConditions () {
        Conditions.put(T,0);
        Conditions.put(P,0);
        Conditions.put(H2,0);
        Conditions.put(N2,0);
        Conditions.put(NH3,0);
        Conditions.put(TMG,0);
        Conditions.put(TMA,0);
        Conditions.put(TMI,0);
        Conditions.put(Si,0);
        Conditions.put(Mg,0);
        Conditions.put(time,0);
        Conditions.put(growth_rate,1);
    }
    public void GeneralLayer() {
        initConditions();
    }
    public void setCondition (Parameters p, Integer value) {
        Conditions.put(p,value);
    }
    public Integer getCondition (Parameters p) {
       return Conditions.get(p);
    }

    //print tha layer at left side. The function toString is tested
    @Override
    public String toString () {
        Set <Parameters> Param = Conditions.keySet();
        Iterator<Parameters> It= Param.iterator();
        StringBuffer s= new StringBuffer();
        int i=0;
        s.append(this.getClass().getSimpleName() + "\n");

        while (It.hasNext()) {
            Parameters parameter=It.next();
            Integer value =Conditions.get(parameter);
            if (value!=0 && i<3) {
                s.append(parameter.toString() + "=" + value + " ");
                i++;
                }
            else if (value!=0 && i>=3) {
                s.append("\n");
                s.append(parameter.toString() + "=" + value + " ");
                i=0;
            }
        }
        return s.toString();
    }

    //print tha layer at right side. The function toString is tested
    public String toStringRight () {
        Set <Parameters> Param = Conditions.keySet();
        Iterator<Parameters> It= Param.iterator();
        StringBuffer s= new StringBuffer();
        int i=0;
        s.append(EmptyString+this.getClass().getSimpleName() + "\n"+EmptyString); //getName or getSimpleName?

        while (It.hasNext()) {
            Parameters parameter=It.next();
            Integer value =Conditions.get(parameter);
            if (value!=0 && i<3) {
                s.append(parameter.toString() + "=" + value + " ");
                i++;
            }
            else if (value!=0 && i>=3) {
                s.append("\n"+EmptyString);
                s.append(parameter.toString() + "=" + value + " ");
                i=0;
            }
        }
        return s.toString();
    }
}

