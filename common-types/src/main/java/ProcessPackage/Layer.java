package ProcessPackage;

import com.sun.javafx.scene.control.skin.IntegerFieldSkin;

import java.util.*;

import static ProcessPackage.Parameters.*;


public class Layer {

    private LayerNames LayerName;
    private Functions Function;
    private String EmptyString= "                                                    ";
    private StringBuffer st=new StringBuffer();
    private LinkedHashMap<Parameters,Integer> Conditions= new LinkedHashMap<Parameters,Integer>();

    public void setConditions (LinkedHashMap<Parameters,Integer> M){
        this.Conditions=M;
    }
    public  LinkedHashMap<Parameters,Integer> getConditions (){
        return Conditions;
    }
    public void setLayerName(LayerNames layerName) {
        LayerName = layerName;
    }
    public void setFunction (Functions f) {
        Function=f;
    }
    public Functions getFunction() {
        return Function;
    }
    public LayerNames getLayerName() {
        return LayerName;
    }

    public void initConditions () {
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

    public void setCondition (Parameters p, Integer value) {
        Conditions.put(p,value);
    }
    public Integer getCondition (Parameters p) {
       return Conditions.get(p);
    }

    @Override
    public String toString () {
        Set <Parameters> Param = Conditions.keySet();
        Iterator<Parameters> It= Param.iterator();
        StringBuffer s= new StringBuffer();
        int i=0;
        s.append(this.Function + " " + this.LayerName + "\n");

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

    public String toStringRight () {
        Set <Parameters> Param = Conditions.keySet();
        Iterator<Parameters> It= Param.iterator();
        StringBuffer s= new StringBuffer();
        int i=0;
        s.append(EmptyString+this.Function + " " + this.LayerName + "\n"+EmptyString);

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



    public String compareTo(Layer l, boolean b) {
        StringBuffer s= new StringBuffer();
        StringBuffer t= new StringBuffer();
        int i=0;

        if (!this.Function.equals(l.Function)) {
            s= new StringBuffer("layers are different");
        return s.toString();
        }
        st=new StringBuffer(EmptyString.subSequence(0,EmptyString.length()-
                this.LayerName.toString().length()- this.Function.toString().length()-1));
        s.append(this.getFunction().toString()+" "+ this.LayerName.toString() +
                st +l.getFunction().toString()+" "+ l.LayerName.toString()+ "\n");
        Set <Parameters> Param = this.Conditions.keySet();
        Iterator<Parameters> It= Param.iterator();
        while (It.hasNext()) {
            Parameters parameter=It.next();
            Integer ThisValue =this.Conditions.get(parameter);
            Integer LValue =l.Conditions.get(parameter);
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

    public String compareTo(Layer l) {
        StringBuffer s= new StringBuffer();
        StringBuffer t= new StringBuffer();
        int i=0;
        if (!this.Function.equals(l.Function)) {
            s= new StringBuffer("layers are different");
            return s.toString();
        }
        //st=new StringBuffer(EmptyString.subSequence(0,EmptyString.length()-this.LayerName.toString().length()));
        s.append(EmptyString + l.LayerName.toString()+ "\n");
        Set <Parameters> Param = this.Conditions.keySet();
        Iterator<Parameters> It= Param.iterator();
        while (It.hasNext()) {
            Parameters parameter=It.next();
            Integer ThisValue =this.Conditions.get(parameter);
            Integer LValue =l.Conditions.get(parameter);
            if (!ThisValue.equals(LValue)) {
                s.append(EmptyString + parameter.toString()+ "=" + LValue + "\n");
            }
        }
        return s.toString();
    }
}

enum Functions {
    Seed_Layer(0), Buffer(1), N_Buffer(2), QW(3), Barrier(4), P_Layer(5), EBLayer(6), SuperLattice(7), InterLayer(8);
    private int index;
    Functions(int i) {index=i;}
    public int getIndex() {return index;}
}
enum LayerNames {GaN, AlGaN, InGaN, InAlGaN, SiN, twoDGaN, threeDGaN, AlN}
enum Parameters {T, P, H2, N2, NH3, TMG, TMA, TMI, Si, Mg, time, growth_rate}