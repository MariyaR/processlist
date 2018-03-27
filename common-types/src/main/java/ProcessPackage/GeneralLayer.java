package ProcessPackage;

import com.sun.javafx.scene.control.skin.IntegerFieldSkin;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static ProcessPackage.Parameters.*;

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "CacheForProcessList")
@Table(name = "layer")
public class GeneralLayer implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "layerid")
    private Long LayerId;
    @Column(name = "layername")
    private String LayerName;
    @Enumerated(EnumType.STRING)
    @Column(name = "layerfunction")
    private Functions Function;
    @Column(name = "temperature")
    private Integer temperature;
    @Column(name = "pressure")
    private Integer pressure;
    @Column(name = "h2")
    private Integer h2;
    @Column(name = "n2")
    private Integer n2;
    @Column(name = "nh3")
    private Integer nh3;
    @Column(name = "tmg")
    private Integer tmg;
    @Column(name = "tma")
    private Integer tma;
    @Column(name = "tmi")
    private Integer tmi;
    @Column(name = "si")
    private Integer si;
    @Column(name = "mg")
    private Integer mg;
    @Column(name = "growthtime")
    private Integer growthTime;
    @Column(name = "growthrate")
    private Integer growthRate;
    @Transient
    private String processName;

    @Transient
    private String EmptyString= "                                                    ";
    @Transient
    private StringBuffer st=new StringBuffer();
    @Transient
    private LinkedHashMap<Parameters,Integer> Conditions= new LinkedHashMap<Parameters,Integer>();//todo переделать через рефлекшн
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "processname",
            foreignKey = @ForeignKey(name = "layer_processname_fkey")
    )
    private GeneralProcess generalProcess;

    public GeneralProcess getGeneralProcess() {
        return generalProcess;
    }

    public void setGeneralProcess(GeneralProcess generalProcess) {
        this.generalProcess = generalProcess;
    }

    public GeneralLayer() {}

    public Long getLayerId() {
        return LayerId;
    }

    public void setLayerId(Long layerId) {
        LayerId = layerId;
    }

    public String getLayerName() {
        return LayerName;
    }

    public void setLayerName(String layerName) {
        LayerName = layerName;
    }

    public Functions getFunction() {return Function;}
    public void setFunction(Functions Function) {this.Function=Function;}

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
        Conditions.put(T,temperature);
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
        Conditions.put(P,pressure);
    }

    public Integer getH2() {
        return h2;
    }

    public void setH2(Integer h2) {
        this.h2 = h2;
        Conditions.put(H2,h2);
    }

    public Integer getN2() {
        return n2;
    }

    public void setN2(Integer n2) {
        this.n2 = n2;
        Conditions.put(N2,n2);
    }

    public Integer getNh3() {
        return nh3;
    }

    public void setNh3(Integer nh3) {
        this.nh3 = nh3;
        Conditions.put(NH3,nh3);
    }

    public Integer getTma() {
        return tma;
    }

    public void setTmg(Integer tmg) {
        this.tmg = tmg;
        Conditions.put(TMG,tmg);
    }

    public Integer getTmg() {
        return tmg;
    }

    public void setTma(Integer tma) {
        this.tma = tma;
        Conditions.put(TMA,tma);
    }

    public Integer getTmi() {
        return tmi;
    }

    public void setTmi(Integer tmi) {
        this.tmi = tmi;
        Conditions.put(TMI,tmi);
    }

    public Integer getSi() {
        return si;
    }

    public void setSi(Integer si) {
        this.si = si;
        Conditions.put(Si,si);
    }

    public Integer getMg() {
        return mg;
    }

    public void setMg(Integer mg) {
        this.mg = mg;
        Conditions.put(Mg,mg);
    }

    public Integer getGrowthTime() {
        return growthTime;
    }

    public void setGrowthTime(Integer growthTime) {
        this.growthTime = growthTime;
        Conditions.put(time,growthTime);
    }

    public Integer getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Integer growthRate) {
        this.growthRate = growthRate;
        Conditions.put(growth_rate,growthRate);
    }

    public void setConditions (LinkedHashMap<Parameters,Integer> M){
        this.Conditions=M;
    }
    public  LinkedHashMap<Parameters,Integer> getConditions (){
        initConditions();
        return Conditions;
    }
    public void initConditions () {
        Conditions.put(T,temperature);
        Conditions.put(P,pressure);
        Conditions.put(H2,h2);
        Conditions.put(N2,n2);
        Conditions.put(NH3,nh3);
        Conditions.put(TMG,tmg);
        Conditions.put(TMA,tma);
        Conditions.put(TMI,tmi);
        Conditions.put(Si,si);
        Conditions.put(Mg,mg);
        Conditions.put(time,growthTime);
        Conditions.put(growth_rate,growthRate);
    }
    public void GeneralLayer() {
        initConditions();
    }
  //  public void setCondition (Parameters p, Integer value) {
  //      Conditions.put(p,value);
  //  }
  //  public Integer getCondition (Parameters p) {
  //     return Conditions.get(p);
  //  }

    //print tha layer at left side. The function toString is tested
    @Override
    public String toString () {
        initConditions();
        Set <Parameters> Param = Conditions.keySet();
        Iterator<Parameters> It= Param.iterator();
        StringBuffer s= new StringBuffer();
        int i=0;
        s.append(this.getFunction().toString() +" "+this.getLayerName() + "\n");

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
                i=1;
            }
        }
        s.append("\n\n");
        return s.toString();
    }

    //print tha layer at right side. The function toString is tested
    public String toStringRight () {
        initConditions();
        Set <Parameters> Param = Conditions.keySet();
        Iterator<Parameters> It= Param.iterator();
        StringBuffer s= new StringBuffer();
        int i=0;
        s.append(EmptyString+this.getFunction().toString()+" " +this.getLayerName()+ "\n"+EmptyString); //getName or getSimpleName?

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
                i=1;
            }
        }
        s.append("\n\n");
        return s.toString();
    }
}

